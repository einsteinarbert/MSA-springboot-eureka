package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;

public interface UsersService {
    Users createUser(Users users);
    Users getUserById(Long id);
    Users updateUser(Users users);
    void updateUserPassword(ChangePasswordDTO users);
}
