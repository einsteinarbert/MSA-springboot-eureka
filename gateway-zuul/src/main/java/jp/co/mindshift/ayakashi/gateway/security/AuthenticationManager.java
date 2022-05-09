package jp.co.mindshift.ayakashi.gateway.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jp.co.mindshift.ayakashi.gateway.model.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
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
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage()));
        }
    }
}
