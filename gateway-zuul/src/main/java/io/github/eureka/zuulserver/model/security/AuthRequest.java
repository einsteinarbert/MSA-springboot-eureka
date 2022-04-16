package io.github.eureka.zuulserver.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 *
 * @author ard333
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequest {
    private String username;
    private String password; // optional when login anonymous
    private String refreshToken; // optional
    private Date birthday; // optional
    private String name; // optional
}
