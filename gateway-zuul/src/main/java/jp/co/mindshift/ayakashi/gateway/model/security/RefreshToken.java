package jp.co.mindshift.ayakashi.gateway.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class RefreshToken {
    private String refreshToken;
    private Integer roleValid; // optional
}
