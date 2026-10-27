package school21.schoolbe2.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school21.schoolbe2.auth.AuthClient;
import school21.schoolbe2.dto.LoginDto;
import school21.schoolbe2.dto.RegisterDto;
import school21.schoolbe2.dto.ResetDto;
import school21.schoolbe2.dto.TokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import school21.schoolbe2.auth.Authorize;
import school21.schoolbe2.dto.ChangePasswordDto;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class AuthController {
    private final AuthClient authClient;

    public AuthController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody RegisterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDto(authClient.register(dto)));
    }

    @PostMapping("/auth")
    public TokenDto auth(@RequestBody LoginDto dto) {
        return new TokenDto(authClient.login(dto));
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> reset(@RequestBody ResetDto dto) {
        authClient.reset(dto.login());
        return ResponseEntity.ok().build();
    }
    @Authorize
    @PostMapping("/auth/change-password")
    public ResponseEntity<Void> changePassword(@Parameter(hidden = true) @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,@RequestBody ChangePasswordDto dto) {
        String token = authorization.trim();
        if (token.regionMatches(true, 0, "Bearer ", 0, 7)) {
            token = token.substring(7).trim();
        }
        authClient.changePassword(token,dto.currentPassword(),dto.newPassword());
        return ResponseEntity.ok().build();
    }
}