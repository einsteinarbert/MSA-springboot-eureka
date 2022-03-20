package io.github.eureka.zuulserver.service;

import io.github.eureka.zuulserver.model.User;
import io.github.eureka.zuulserver.model.security.Role;
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
public class UserService {

    private Map<String, User> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();

        //username:passwowrd -> user:user
        data.put("user", new User("user", "SRfptuBEzhKHTCkPs2FPSxoKCRhTtbC4PT3XjUyNlJ0=", true, List.of(Role.ROLE_USER)));

        //username:passwowrd -> admin:admin
        data.put("admin", new User("admin", "must insert password encoded here for test", true, List.of(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username) {
        Mono<User>  res = Mono.justOrEmpty(data.get(username));
        return res;
    }
}
