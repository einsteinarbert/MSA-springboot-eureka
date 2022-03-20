package io.github.eureka.zuulserver.rest;

import io.github.eureka.zuulserver.model.security.AuthRequest;
import io.github.eureka.zuulserver.model.security.AuthResponse;
import io.github.eureka.zuulserver.security.JWTUtil;
import io.github.eureka.zuulserver.security.PBKDF2Encoder;
import io.github.eureka.zuulserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/auth/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
