package io.github.eureka.zuulserver.repository;

import io.github.eureka.zuulserver.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsernameAndStatus(String username, Integer status);
    Optional<Users> findByRefreshTokenAndStatus(String token, Integer status);
}
