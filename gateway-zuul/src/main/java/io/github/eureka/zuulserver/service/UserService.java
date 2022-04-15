package io.github.eureka.zuulserver.service;

import io.github.eureka.zuulserver.model.Users;
import io.github.eureka.zuulserver.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Service
@AllArgsConstructor
public class UserService {
    private UsersRepository usersRepository;
    public Mono<Users> findByUsername(String username) {
        return Mono.justOrEmpty(usersRepository.findByUsernameAndStatus(username, 1));
    }
    public Optional<Users> findByRefreshToken(String token) {
        return usersRepository.findByRefreshTokenAndStatus(token, 1);
    }
}
