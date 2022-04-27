package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.form.ChangePasswordForm;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.CreateUserForm;
import jp.co.mindshift.ayakashi.api.model.dto.UserDataDTO;
import jp.co.mindshift.ayakashi.api.model.form.UserSettingForm;

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
