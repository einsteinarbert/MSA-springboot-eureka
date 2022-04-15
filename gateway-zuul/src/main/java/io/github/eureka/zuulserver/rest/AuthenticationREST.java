package io.github.eureka.zuulserver.rest;

import io.github.eureka.zuulserver.model.Users;
import io.github.eureka.zuulserver.model.security.AuthRequest;
import io.github.eureka.zuulserver.model.security.AuthResponse;
import io.github.eureka.zuulserver.model.security.RefreshToken;
import io.github.eureka.zuulserver.repository.UsersRepository;
import io.github.eureka.zuulserver.security.JWTUtil;
import io.github.eureka.zuulserver.security.PBKDF2Encoder;
import io.github.eureka.zuulserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final UsersRepository usersRepository;

    @PostMapping("/auth/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getEncryptedPassword()))
                .map(userDetails -> {
                            String refreshToken = jwtUtil.generateRefreshToken(userDetails);
                            userDetails.setRefreshToken(refreshToken);
                            usersRepository.save(userDetails);
                            return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails), refreshToken));
                        }
                )
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshToken refreshToken) {
        try {
            boolean valid = jwtUtil.validateRefreshToken(refreshToken.getRefreshToken());
            Optional<Users> users = userService.findByRefreshToken(refreshToken.getRefreshToken());
            if (valid && users.isPresent()) {
                var userDetails = users.get();
                // generate new token
                String refreshTokenNew = jwtUtil.generateRefreshToken(userDetails);
                userDetails.setRefreshToken(refreshTokenNew);
                usersRepository.save(userDetails);
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails), refreshTokenNew));
            }
        } catch (Exception e) {
            log.error("Refresh token error", e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
