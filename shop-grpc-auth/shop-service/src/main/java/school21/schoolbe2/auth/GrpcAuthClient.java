package school21.schoolbe2.auth;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;
import school21.auth.grpc.AuthGrpc;
import school21.auth.grpc.LoginRequest;
import school21.auth.grpc.RegisterRequest;
import school21.auth.grpc.ResetPasswordRequest;
import school21.auth.grpc.VerifyTokenRequest;
import school21.auth.grpc.VerifyTokenResponse;
import school21.schoolbe2.dto.LoginDto;
import school21.schoolbe2.dto.RegisterDto;
import school21.schoolbe2.exception.BadRequestException;
import school21.schoolbe2.exception.ConflictException;
import school21.schoolbe2.exception.NotFoundException;
import school21.schoolbe2.exception.ServiceUnavailableException;
import school21.schoolbe2.exception.UnauthorizedException;
import school21.auth.grpc.ChangePasswordRequest;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
public class GrpcAuthClient implements AuthClient, TokenValidator {
    private final AuthGrpc.AuthBlockingStub stub;

    public GrpcAuthClient(AuthGrpc.AuthBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public String register(RegisterDto dto) {
        RegisterRequest request = RegisterRequest.newBuilder()
                .setName(nullToEmpty(dto.name()))
                .setSurname(nullToEmpty(dto.surname()))
                .setPhone(nullToEmpty(dto.phone()))
                .setLogin(nullToEmpty(dto.login()))
                .setPassword(nullToEmpty(dto.password()))
                .build();
        return call(() -> stub().register(request).getToken());
    }

    @Override
    public String login(LoginDto dto) {
        LoginRequest request = LoginRequest.newBuilder()
                .setLogin(nullToEmpty(dto.login()))
                .setPassword(nullToEmpty(dto.password()))
                .build();
        return call(() -> stub().login(request).getToken());
    }

    @Override
    public void reset(String login) {
        ResetPasswordRequest request = ResetPasswordRequest.newBuilder()
                .setLogin(nullToEmpty(login))
                .build();
        call(() -> stub().resetPassword(request));
    }

    @Override
    public TokenInfo verify(String token) {
        VerifyTokenRequest request = VerifyTokenRequest.newBuilder()
                .setToken(nullToEmpty(token))
                .build();
        VerifyTokenResponse response = call(() -> stub().verifyToken(request));
        return new TokenInfo(response.getValid(), response.getUserId());
    }

    private AuthGrpc.AuthBlockingStub stub() {
        return stub.withDeadlineAfter(5, TimeUnit.SECONDS);
    }

    @Override
    public void changePassword(String token, String currentPassword, String newPassword) {
        ChangePasswordRequest request = ChangePasswordRequest.newBuilder()
            .setToken(nullToEmpty(token))
            .setCurrentPassword(nullToEmpty(currentPassword))
            .setNewPassword(nullToEmpty(newPassword))
            .build();

        call(() -> stub().changePassword(request));
    }

    private <T> T call(Supplier<T> action) {
        try {
            return action.get();
        } catch (StatusRuntimeException e) {
            String message = e.getStatus().getDescription();
            Status.Code code = e.getStatus().getCode();
            switch (code) {
                case INVALID_ARGUMENT:
                    throw new BadRequestException(message);
                case UNAUTHENTICATED:
                    throw new UnauthorizedException(message);
                case ALREADY_EXISTS:
                    throw new ConflictException(message);
                case NOT_FOUND:
                    throw new NotFoundException(message);
                default:
                    throw new ServiceUnavailableException("Authorization service is unavailable");
            }
        }
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}