package io.github.eureka.api.service;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.form.ChangePasswordForm;
import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.form.CreateUserForm;
import io.github.eureka.api.model.dto.UserDataDTO;
import io.github.eureka.api.model.form.UserSettingForm;

public interface UsersService {
    ResponseDTO<?> createUser(CreateUserForm users);
	ResponseDTO<?> getUserById(Long id);
	ResponseDTO<?> updateUser(Users users);
	ResponseDTO<?> updateUserPassword(ChangePasswordForm users);

	ResponseDTO<?> getAllUser();

	UserDataDTO getDataUserInMyPage(Long userId);
    void saveSettingData(UserSettingForm data);

	UserDataDTO getDataUserInMyPageWithDevice(String deviceId);
}
