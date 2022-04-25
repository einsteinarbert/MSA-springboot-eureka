package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;
import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.form.CreateUserForm;
import io.github.eureka.api.model.dto.UserDataDTO;
import io.github.eureka.api.model.dto.UserSettingDTO;

import java.util.List;

public interface UsersService {
    ResponseDTO<?> createUser(CreateUserForm users);
    Users getUserById(Long id);
	ResponseDTO<?> updateUser(Users users);
	ResponseDTO<?> updateUserPassword(ChangePasswordDTO users);

	List<Users> getAllUser();

	UserDataDTO getDataUserInMyPage(Long userId);
    void saveSettingData(UserSettingDTO data);

	UserDataDTO getDataUserInMyPageWithDevice(String deviceId);
}
