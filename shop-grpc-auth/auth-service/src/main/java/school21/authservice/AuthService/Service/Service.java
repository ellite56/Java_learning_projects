package school21.authservice.AuthService.Service;

import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import school21.authservice.Dto.TokenValidationDto;
import school21.authservice.Dto.UserDto;
import school21.authservice.Exception.BadRequestException;
import school21.authservice.Exception.InvalidCredentialsException;
import school21.authservice.Exception.NotFoundException;
import school21.authservice.Exception.UserAlreadyExistsException;
import school21.authservice.Mapper.Mapper;
import school21.authservice.Persistence.Entity.UserEntity;
import school21.authservice.Persistence.Repository.UserReposotory;
import jakarta.validation.Validator;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@org.springframework.stereotype.Service
public class Service implements AuthService {
    private final UserReposotory userReposotory;
    private final Mapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final SecureRandom random = new SecureRandom();
    private final Validator validator;

    public Service(UserReposotory userReposotory, Mapper mapper, PasswordEncoder encode, JwtService jwtService,Validator validator) {
        this.userReposotory = userReposotory;
        this.mapper = mapper;
        this.encoder = encode;
        this.jwtService = jwtService;
        this.validator=validator;
    }

    @Override
    public String Register(UserDto userDto) {
        if (userDto == null) {
            throw new BadRequestException("The registration data has not been transmitted");
        }
        var violations = validator.validate(userDto);
        if (!violations.isEmpty()){
            throw new BadRequestException("The login should be an email");
        }
        if (userDto.getLogin() == null || userDto.getLogin().isBlank() || userDto.getName() == null || userDto.getName().isBlank() ||
                userDto.getSurname() == null || userDto.getSurname().isBlank() || userDto.getPhone() == null || userDto.getPhone().isBlank()
                || userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            throw new BadRequestException("Fields is null");
        }
        if (userDto.getName().length() > 50 || userDto.getSurname().length() > 50 || userDto.getPhone().length() > 25 || userDto.getLogin().length() > 100){
            throw new BadRequestException("Exceeding the maximum allowed number of characters");
        }
        if (userReposotory.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistsException("The email has already been registered");
        }
        String hash = encoder.encode(userDto.getPassword());
        UserEntity userEntity;
        try {userEntity = userReposotory.save(mapper.dtoToEntity(userDto, hash));
        } catch (DataIntegrityViolationException e) {
            if (userReposotory.existsByLogin(userDto.getLogin())) {
                throw new UserAlreadyExistsException("The email has already been registered");
            }
            throw e;
        }
        return jwtService.generateToken(userEntity.getid());
    }

    @Override
    public String login(String login, String password) {
        if (login == null || password == null) {
            throw new BadRequestException("The login and password are required.");
        }
        if (login.isBlank() || password.isBlank()) {
            throw new BadRequestException("Error.Login is empty or password is empty");
        }
        UserEntity userEntity = userReposotory.findByLogin(login).orElseThrow(() -> new InvalidCredentialsException("Invalid login or password"));
        if (!encoder.matches(password, userEntity.getPasswordhash())) {
            throw new InvalidCredentialsException("Invalid login or password");
        }
        return jwtService.generateToken(userEntity.getid());
    }

    @Override
    public TokenValidationDto verifyToken(String token) {
        try {
            UUID uuid = jwtService.validateAndGetUserId(token);
            return new TokenValidationDto(true, uuid);
        } catch (JwtException | IllegalArgumentException e) {
            return new TokenValidationDto(false, null);
        }
    }

    @Override
    public void changePassword(String token, String currentPassword, String newPassword) {
        if (token == null || token.isBlank() || currentPassword == null || currentPassword.isBlank() || newPassword == null || newPassword.isBlank()) {
            throw new BadRequestException("The token, current password, and new password are required");
        }
        UUID uuid = jwtService.validateAndGetUserId(token);
        UserEntity userEntity = userReposotory.findById(uuid).orElseThrow(() -> new NotFoundException("User not found"));
        if (!encoder.matches(currentPassword, userEntity.getPasswordhash())) {
            throw new InvalidCredentialsException("The current password is incorrect");
        }
        userEntity.setPasswordhash(encoder.encode(newPassword));
        userReposotory.save(userEntity);
    }

    @Override
    public void resetPassword(String login) {
        if (login == null || login.isBlank()) {
            throw new BadRequestException("Mail is mandatory");
        }
        UserEntity userEntity = userReposotory.findByLogin(login).orElseThrow(() -> new NotFoundException("User not found"));
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        String newPassword = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        userEntity.setPasswordhash(encoder.encode(newPassword));
        userReposotory.save(userEntity);
        System.out.println(newPassword);
    }
}
