package io.github.eureka.zuulserver.rest;

import com.google.gson.Gson;
import io.github.eureka.zuulserver.common.Constant;
import io.github.eureka.zuulserver.common.Helper;
import io.github.eureka.zuulserver.model.Users;
import io.github.eureka.zuulserver.model.dto.BaseMsgDTO;
import io.github.eureka.zuulserver.model.security.AuthRequest;
import io.github.eureka.zuulserver.model.security.AuthResponse;
import io.github.eureka.zuulserver.model.security.RefreshToken;
import io.github.eureka.zuulserver.repository.UsersRepository;
import io.github.eureka.zuulserver.security.JWTUtil;
import io.github.eureka.zuulserver.security.PBKDF2Encoder;
import io.github.eureka.zuulserver.service.UserService;
import io.github.eureka.zuulserver.service.feign.UserApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final UsersRepository usersRepository;
    private final UserApiService userApiService;

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
    public ResponseEntity<?> refreshToken(@RequestBody RefreshToken refreshToken) {
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
            return ResponseEntity.badRequest().body(
                    BaseMsgDTO.builder()
                            .code(400)
                            .message(e.getMessage())
                            .status(BaseMsgDTO.NG)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
        // has token
        if (StringUtils.hasLength(ar.getRefreshToken())) {
            boolean valid = jwtUtil.validateRefreshToken(ar.getRefreshToken());
            if (valid) {
                return refreshToken(new RefreshToken(ar.getRefreshToken(), 0));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(!StringUtils.hasLength(ar.getUsername())) {
            ar.setUsername(Helper.generateUserName());
        }
        Optional<Users> usr = usersRepository.findByUsernameAndStatusIn(ar.getUsername(), List.of(0, 1));
        if (usr.isPresent()) {
            Users users = usr.get();
            // user registered will not able to login anonymous
            if (users.getStatus() == 1) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            // generate new token
            String refreshTokenNew = jwtUtil.generateRefreshToken(users);
            users.setRefreshToken(refreshTokenNew);
            usersRepository.save(users);
            return BaseMsgDTO.success(new AuthResponse(jwtUtil.generateToken(users), refreshTokenNew));
        } else {
            try {
                Users newUsr = new Users();
                newUsr.setBirthday(new SimpleDateFormat(Constant.DATE_FORMAT).parse(ar.getBirthday()));
                newUsr.setName(ar.getName());
                newUsr.setUsername(ar.getUsername());
                newUsr.setDeviceId(ar.getDeviceId());

                return userApiService.createNewUser(newUsr).filter(StringUtils::hasLength)
                        .defaultIfEmpty("")
                        .map(s -> {
                            if (StringUtils.hasLength(s)) {
                                var base = new BaseMsgDTO<String>();
                                try {
                                    return new Gson().fromJson(s, base.getClass());
                                } catch (Exception e) {
                                    return BaseMsgDTO.builder()
                                            .code(HttpStatus.BAD_REQUEST.value())
                                            .message(e.getMessage())
                                            .status(BaseMsgDTO.NG)
                                            .build();
                                }
                            } else {
                                // generate new token
                                var created = usersRepository.findByUsernameAndStatus(newUsr.getUsername(), 0);
                                if (created != null && created.getId() == null) {
                                    return BaseMsgDTO.builder()
                                            .code(400)
                                            .message("Cannot create user")
                                            .status(BaseMsgDTO.NG)
                                            .build();
                                }
                                String refreshTokenNew = jwtUtil.generateRefreshToken(created);
                                created.setRefreshToken(refreshTokenNew);
                                usersRepository.save(created);
                                return new BaseMsgDTO<>(new AuthResponse(jwtUtil.generateToken(created), refreshTokenNew));
                            }
                        });
            } catch (Exception e) {
                log.error("Cannot call api create user", e);
                return BaseMsgDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .status(BaseMsgDTO.NG)
                        .build();
            }
        }
    }
}
