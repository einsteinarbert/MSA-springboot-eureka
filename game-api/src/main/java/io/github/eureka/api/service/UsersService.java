package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;

public interface UsersService {
    Users createUser(Users users);

    Users getUserById(Long id);

    Users updateUser(Users users);
}
