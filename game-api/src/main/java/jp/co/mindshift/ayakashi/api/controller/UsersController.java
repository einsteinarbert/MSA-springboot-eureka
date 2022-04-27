package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.dto.UserDataDTO;
import jp.co.mindshift.ayakashi.api.model.form.ChangePasswordForm;
import jp.co.mindshift.ayakashi.api.model.form.CreateUserForm;
import jp.co.mindshift.ayakashi.api.model.form.UserSettingForm;
import jp.co.mindshift.ayakashi.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Validated
public class UsersController extends BaseController{
    private final UsersService usersService;

    @PostMapping("/users/create")
    public ResponseDTO<?> createUser(@Valid @RequestBody CreateUserForm users){
        return usersService.createUser(users);
    }


    @PostMapping("/users/update")
    public ResponseDTO<?> updateUser(@Valid @RequestBody Users users){
        return usersService.updateUser(users);
    }

    @PostMapping("/change-password")
    public ResponseDTO<?> updateUserPassword(@Valid @RequestBody ChangePasswordForm users){
        return usersService.updateUserPassword(users);
    }

    @GetMapping("/user-info/{id}")
    public ResponseDTO<?> getOneUser(@PathVariable Long id){
        return usersService.getUserById(id);
    }

    @GetMapping("/list-user")
    public ResponseDTO<?> getAllUser(){
        return usersService.getAllUser();
    }
    
    @GetMapping("/user-data/{userId}")
    public ResponseDTO<?> getUserDataInMyPage(@Min(message = "userId.null", value = 1L) @PathVariable Long userId){
        UserDataDTO userDataEntity = usersService.getDataUserInMyPage(userId);
        return ResponseDTO.success(userDataEntity);
    }


    @GetMapping("/user-data-device/{deviceId}")
    public ResponseDTO<?> getUserDataInMyPage(@PathVariable String deviceId){
        UserDataDTO userDataEntity = usersService.getDataUserInMyPageWithDevice(deviceId);
        return ResponseDTO.success(userDataEntity);
    }

    @PostMapping("/user-setting")
    public ResponseDTO<?> setting(HttpServletRequest request, @Valid @RequestBody UserSettingForm data){
        setUserInfo(request);
        usersService.saveSettingData(data);
        return ResponseDTO.success("");
    }

}
