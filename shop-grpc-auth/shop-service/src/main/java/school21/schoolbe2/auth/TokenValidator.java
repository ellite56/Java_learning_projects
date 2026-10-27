package school21.schoolbe2.auth;

public interface TokenValidator {
    TokenInfo verify(String token);
}