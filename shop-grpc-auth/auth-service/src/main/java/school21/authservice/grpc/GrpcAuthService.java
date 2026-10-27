package school21.authservice.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.JwtException;
import org.springframework.grpc.server.service.GrpcService;
import school21.auth.grpc.AuthGrpc;
import school21.auth.grpc.ChangePasswordRequest;
import school21.auth.grpc.Empty;
import school21.auth.grpc.LoginRequest;
import school21.auth.grpc.RegisterRequest;
import school21.auth.grpc.ResetPasswordRequest;
import school21.auth.grpc.TokenResponse;
import school21.auth.grpc.VerifyTokenRequest;
import school21.auth.grpc.VerifyTokenResponse;
import school21.authservice.AuthService.Service.AuthService;
import school21.authservice.Dto.TokenValidationDto;
import school21.authservice.Dto.UserDto;
import school21.authservice.Exception.BadRequestException;
import school21.authservice.Exception.InvalidCredentialsException;
import school21.authservice.Exception.NotFoundException;
import school21.authservice.Exception.UserAlreadyExistsException;

import java.util.UUID;
import java.util.function.Supplier;

@GrpcService
public class GrpcAuthService extends AuthGrpc.AuthImplBase {
    private final AuthService authService;

    public GrpcAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void register(RegisterRequest request, StreamObserver<TokenResponse> responseObserver) {
        handle(responseObserver, () -> {
            UserDto userDto = new UserDto(request.getName(), request.getSurname(), request.getPhone(),
                    request.getLogin(), request.getPassword());
            String token = authService.Register(userDto);
            return TokenResponse.newBuilder().setToken(token).build();
        });
    }

    @Override
    public void login(LoginRequest request, StreamObserver<TokenResponse> responseObserver) {
        handle(responseObserver, () -> {
            String token = authService.login(request.getLogin(), request.getPassword());
            return TokenResponse.newBuilder().setToken(token).build();
        });
    }

    @Override
    public void verifyToken(VerifyTokenRequest request, StreamObserver<VerifyTokenResponse> responseObserver) {
        handle(responseObserver, () -> {
            TokenValidationDto result = authService.verifyToken(request.getToken());
            UUID userId = result.getId();
            String userIdNew;
            if(userId == null) {
                userIdNew = "";
            } else {userIdNew = result.getId().toString();}
            return VerifyTokenResponse.newBuilder().setValid(result.isValid()).setUserId(userIdNew).build();
        });
    }

    @Override
    public void changePassword(ChangePasswordRequest request, StreamObserver<Empty> responseObserver) {
        handle(responseObserver, () -> {
            authService.changePassword(request.getToken(), request.getCurrentPassword(), request.getNewPassword());
            return Empty.getDefaultInstance();
        });
    }

    @Override
    public void resetPassword(ResetPasswordRequest request, StreamObserver<Empty> responseObserver) {
        handle(responseObserver, () -> {
            authService.resetPassword(request.getLogin());
            return Empty.getDefaultInstance();
        });
    }

    private <T> void handle(StreamObserver<T> responseObserver, Supplier<T> action) {
        try {
            responseObserver.onNext(action.get());
            responseObserver.onCompleted();
        } catch (BadRequestException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException());
        } catch (InvalidCredentialsException | JwtException e) {
            responseObserver.onError(Status.UNAUTHENTICATED.withDescription(e.getMessage()).asRuntimeException());
        } catch (UserAlreadyExistsException e) {
            responseObserver.onError(Status.ALREADY_EXISTS.withDescription(e.getMessage()).asRuntimeException());
        } catch (NotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (RuntimeException e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Internal error").asRuntimeException());
        }
    }
}