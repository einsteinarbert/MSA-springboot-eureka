package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;
import io.github.eureka.api.model.dto.UserDataEntity;

import java.util.List;

public interface UsersService {
    Users createUser(Users users);
    Users getUserById(Long id);
    Users updateUser(Users users);
    void updateUserPassword(ChangePasswordDTO users);

	List<Users> getAllUser();

	UserDataEntity getDataUserInMyPage(Long userId);
}
