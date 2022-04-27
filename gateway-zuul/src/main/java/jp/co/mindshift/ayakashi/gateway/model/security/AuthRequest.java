package jp.co.mindshift.ayakashi.gateway.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author ard333
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequest {
    @NotBlank
    private String deviceId;
    private String username;
    private String password; // optional when login anonymous
    private String refreshToken; // optional
    private String birthday; // optional
    private String name; // optional
    private Integer gender; // optional
}
