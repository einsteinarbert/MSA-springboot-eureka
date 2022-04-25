package io.github.eureka.api.controller;

import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UsersController extends BaseController{
    private final UsersService usersService;

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO users){
            usersService.createUser(users);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestBody Users users){
        usersService.updateUser(users);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody ChangePasswordDTO users){
        usersService.updateUserPassword(users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-info/{id}")
    public ResponseDTO<?> getOneUser(@PathVariable Long id){
        Users users = usersService.getUserById(id);
        return ResponseDTO.success(users);
    }

    @GetMapping("/list-user")
    public ResponseDTO<?> getAllUser(){
        List<Users> users = usersService.getAllUser();
        return ResponseDTO.success(users);
    }
    
    @GetMapping("/user-data/{userId}")
    public ResponseDTO<?> getUserDataInMyPage(@PathVariable Long userId){
        Assert.isTrue(userId > 0, MsgUtil.getMessage("user.id.invalid"));
        UserDataDTO userDataEntity = usersService.getDataUserInMyPage(userId);
        return ResponseDTO.success(userDataEntity);
    }


    @GetMapping("/user-data-device/{deviceId}")
    public ResponseDTO<?> getUserDataInMyPage(@PathVariable String deviceId){
        UserDataDTO userDataEntity = usersService.getDataUserInMyPageWithDevice(deviceId);
        return ResponseDTO.success(userDataEntity);
    }

    @PostMapping("/user-setting")
    public ResponseDTO<?> setting(HttpServletRequest request, @RequestBody UserSettingDTO data){
        setUserInfo(request);
        usersService.saveSettingData(data);
        return ResponseDTO.success("");
    }

}
