package io.github.eureka.zuulserver.repository;

import io.github.eureka.zuulserver.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
