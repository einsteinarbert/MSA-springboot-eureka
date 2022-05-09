package jp.co.mindshift.ayakashi.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private JWTUtil jwtUtil;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        try {
            String authToken = authentication.getCredentials().toString();
            String username = jwtUtil.getUsernameFromToken(authToken);
            return Mono.just(jwtUtil.validateToken(authToken))
                    .filter(valid -> valid)
                    .switchIfEmpty(Mono.empty())
                    .map(valid -> {
                        Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                        List<String> rolesMap = claims.get("role", List.class);
                        return new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                        );
                    });
        } catch (ExpiredJwtException e) {
            log.error("JWT expired", e);
            throw new IllegalArgumentException("JWT expired at " + e.getClaims().getExpiration());
        }
    }
}
