package io.github.eureka.zuulserver.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AuthResponse {
    private String token;
    private String refreshToken;
    private Long userId;
    private String username;
}
