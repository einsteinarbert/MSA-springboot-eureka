package io.github.eureka.zuulserver.service;

import com.google.gson.Gson;
import io.github.eureka.zuulserver.common.Constant;
import io.github.eureka.zuulserver.common.Helper;
import io.github.eureka.zuulserver.common.ResponseCode;
import io.github.eureka.zuulserver.model.Users;
import io.github.eureka.zuulserver.model.dto.ResponseDTO;
import io.github.eureka.zuulserver.model.security.AuthRequest;
import io.github.eureka.zuulserver.model.security.AuthResponse;
import io.github.eureka.zuulserver.model.security.RefreshToken;
import io.github.eureka.zuulserver.repository.UsersRepository;
import io.github.eureka.zuulserver.security.JWTUtil;
import io.github.eureka.zuulserver.service.feign.UserApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UsersRepository usersRepository;
    private UserApiService userApiService;
    private final JWTUtil jwtUtil;

    public Mono<Users> findByUsername(String username) {
        return Mono.justOrEmpty(usersRepository.findByUsernameAndStatus(username, 1));
    }
    public Optional<Users> findByRefreshToken(String token, String... args) {
        List<Integer> status = (args.length == 1) ? List.of(Integer.parseInt(args[0])) : List.of(0, 1);
        return usersRepository.findByRefreshTokenAndStatusIn(token, status);
    }

    public AuthResponse refreshToken(@RequestBody RefreshToken refreshToken) {
        boolean valid = jwtUtil.validateRefreshToken(refreshToken.getRefreshToken());
        Optional<Users> users = findByRefreshToken(refreshToken.getRefreshToken());
        if (valid && users.isPresent()) {
            var userDetails = users.get();
            // generate new token
            String refreshTokenNew = jwtUtil.generateRefreshToken(userDetails);
            userDetails.setRefreshToken(refreshTokenNew);
            usersRepository.save(userDetails);
            return new AuthResponse(jwtUtil.generateToken(userDetails), refreshTokenNew, userDetails.getId(), userDetails.getUsername());
        }
        return null;
    }

    public Object loginAnonymous(AuthRequest ar) {
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
        Users users = usersRepository.findByUsernameAndStatusIn(ar.getUsername(), List.of(0, 1));
        // TODO: mock deviceId to old user
        if (null == users) {
            List<Users> list = usersRepository.findByDeviceIdAndStatusIn(ar.getDeviceId(), List.of(0, 1));
            if (list.size() != 0) {
                users = list.get(0);
            }
        }
        if (users != null && users.getId() != null) {
            // user registered will not able to login anonymous
            if (users.getStatus() == 1) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            // generate new token
            String refreshTokenNew = jwtUtil.generateRefreshToken(users);
            users.setRefreshToken(refreshTokenNew);
            users = usersRepository.save(users);
            return ResponseEntity.ok(ResponseDTO.success(new AuthResponse(jwtUtil.generateToken(users), refreshTokenNew, users.getId(), users.getUsername())));
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
                                var base = new ResponseDTO<String>();
                                try {
                                    return new Gson().fromJson(s, base.getClass());
                                } catch (Exception e) {
                                    return ResponseEntity.ok(ResponseDTO.builder()
                                            .status(ResponseCode.FAILURE)
                                            .message(e.getMessage())
                                            .code(ResponseDTO.NG)
                                            .build());
                                }
                            } else {
                                // generate new token
                                var created = usersRepository.findByUsernameAndStatus(newUsr.getUsername(), 0);
                                if (created == null || created.getId() == null) {
                                    return ResponseEntity.ok(ResponseDTO.builder()
                                            .status(ResponseCode.FAILURE)
                                            .message("Cannot create user")
                                            .code(ResponseDTO.NG)
                                            .build());
                                }
                                String refreshTokenNew = jwtUtil.generateRefreshToken(created);
                                created.setRefreshToken(refreshTokenNew);
                                created = usersRepository.save(created);
                                return ResponseEntity.ok(new ResponseDTO<>(new AuthResponse(jwtUtil.generateToken(created), refreshTokenNew, created.getId(), created.getUsername())));
                            }
                        });
            } catch (Exception e) {
                log.error("Cannot call api create user", e);
                return ResponseEntity.ok(ResponseDTO.builder()
                        .status(ResponseCode.FAILURE)
                        .message(e.getMessage())
                        .code(ResponseDTO.NG)
                        .build());
            }
        }
    }
}
