package school21.authservice.AuthService.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;


import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtService {
    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(UUID uuid) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(Duration.ofMinutes(20));
        return Jwts.builder().subject(uuid.toString()).issuedAt(Date.from(now)).expiration(Date.from(expiresAt)).signWith(secretKey).compact();
    }

    public UUID validateAndGetUserId(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Error.Token is null or empty");
        }
        String sub = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
        if (sub ==null || sub.isBlank()){
            throw new IllegalArgumentException("Token does not contain a user ID");
        }
        return UUID.fromString(sub);
    }

}
