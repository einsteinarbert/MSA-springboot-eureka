package jp.co.mindshift.ayakashi.gateway.rest;

import jp.co.mindshift.ayakashi.gateway.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.gateway.model.security.AuthRequest;
import jp.co.mindshift.ayakashi.gateway.model.security.AuthResponse;
import jp.co.mindshift.ayakashi.gateway.model.security.RefreshToken;
import jp.co.mindshift.ayakashi.gateway.repository.UsersRepository;
import jp.co.mindshift.ayakashi.gateway.security.JWTUtil;
import jp.co.mindshift.ayakashi.gateway.security.PBKDF2Encoder;
import jp.co.mindshift.ayakashi.gateway.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
                            return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails), refreshToken, userDetails.getId(), userDetails.getUsername()));
                        }
                )
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshToken refreshToken) {
        try {
            AuthResponse authResponse = userService.refreshToken(refreshToken);
            return null == authResponse ? ResponseEntity.ok(ResponseDTO.builder().statusCode(HttpStatus.UNAUTHORIZED.value()).build())
                    : ResponseEntity.ok(ResponseDTO.builder().data(authResponse).build());
        } catch (Exception e) {
            log.error("Refresh token error", e);
            return ResponseEntity.ok(
                    ResponseDTO.builder()
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/auth/form/login-anonymous")
    public Object loginAnonymousForm(@ModelAttribute AuthRequest ar) {
        return loginAnonymous(ar);
    }

    /**
     * Auto create new user for trial play game
     *
     * @param ar user info
     * @return authentication token
     */
    @PostMapping("/auth/login-anonymous")
    public Object loginAnonymous(@RequestBody AuthRequest ar) {
        return userService.loginAnonymous(ar);
    }
}
