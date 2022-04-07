package io.github.eureka.zuulserver.service;

import io.github.eureka.zuulserver.model.User;
import io.github.eureka.zuulserver.model.Users;
import io.github.eureka.zuulserver.model.security.Role;
import io.github.eureka.zuulserver.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Service
@AllArgsConstructor
public class UserService {
    private UsersRepository usersRepository;
    public Mono<Users> findByUsername(String username) {
        Mono<Users>  res = Mono.justOrEmpty(usersRepository.findByUsername(username));
        return res;
    }
}
