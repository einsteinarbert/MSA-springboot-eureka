package io.github.eureka.zuulserver.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PBKDF2Encoder implements PasswordEncoder {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.iteration}")
    private Integer iteration;

    @Value("${jwt.keylength}")
    private Integer keyLength;

    /**
     * More info (https://www.owasp.org/index.php/Hashing_Java) 404 :(
     * @param cs password
     * @return encoded password
     */
    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                                            .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keyLength))
                                            .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }
}
