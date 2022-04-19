package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;
import io.github.eureka.api.model.dto.CreateUserDTO;
import io.github.eureka.api.model.dto.UserDataDTO;
import io.github.eureka.api.model.dto.UserDataEntity;

import java.util.List;

public interface UsersService {
    Users createUser(CreateUserDTO users);
    Users getUserById(Long id);
    Users updateUser(Users users);
    void updateUserPassword(ChangePasswordDTO users);

	List<Users> getAllUser();

	UserDataDTO getDataUserInMyPage(Long userId);
}
