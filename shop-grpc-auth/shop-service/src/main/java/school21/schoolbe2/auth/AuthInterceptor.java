package school21.schoolbe2.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import school21.schoolbe2.exception.UnauthorizedException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenValidator tokenValidator;

    public AuthInterceptor(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        boolean hasMethodAnnotation = handlerMethod.hasMethodAnnotation(Authorize.class);
        boolean hasTypeAnnotation = handlerMethod.getBeanType().isAnnotationPresent(Authorize.class);

        if (!hasMethodAnnotation && !hasTypeAnnotation) {
            return true;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isBlank()) {
            throw new UnauthorizedException("Authorization header is missing");
        }

        String token = authHeader.trim();
        if (token.regionMatches(true, 0, "Bearer", 0, 6)) {
            token = token.substring(6).trim();
        }

        if (token.isEmpty()) {
            throw new UnauthorizedException("Authorization token is missing");
        }

        TokenInfo tokenInfo = tokenValidator.verify(token);
        if (tokenInfo == null || !tokenInfo.valid()) {
            throw new UnauthorizedException("Invalid or expired token");
        }

        if (tokenInfo.userId() != null && !tokenInfo.userId().isBlank()) {
            request.setAttribute("userId", tokenInfo.userId());
        }

        return true;
    }
}
