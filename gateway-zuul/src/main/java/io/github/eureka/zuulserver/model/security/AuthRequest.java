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
public class  AuthRequest {
    private String username;
    private String password; // optional when login anonymous
    private String refreshToken; // optional
    private String birthday; // optional
    private String name; // optional
}
