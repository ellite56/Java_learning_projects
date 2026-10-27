package school21.authservice.AuthService.Service;

import school21.authservice.Dto.TokenValidationDto;
import school21.authservice.Dto.UserDto;

public interface AuthService {
    String Register(UserDto userDto);
    String login(String login, String password);
    TokenValidationDto verifyToken(String token);
    void changePassword(String token, String currentPassword, String newPassword);
    void resetPassword(String login);

}
