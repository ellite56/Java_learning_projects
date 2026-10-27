package school21.schoolbe2.auth;

import school21.schoolbe2.dto.LoginDto;
import school21.schoolbe2.dto.RegisterDto;

public interface AuthClient {
    String register(RegisterDto dto);
    String login(LoginDto dto);
    void reset(String login);
    void changePassword(String token, String currentPassword, String newPassword);
}