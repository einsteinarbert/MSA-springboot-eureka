package io.github.eureka.api.controller;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UsersController extends BaseController{
    private final UsersService usersService;

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(HttpServletRequest request, @RequestBody CreateUserDTO users){
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
    public BaseMsgDTO<?> getOneUser(@PathVariable Long id){
        Users users = usersService.getUserById(id);
        return BaseMsgDTO.success(users);
    }

    @GetMapping("/list-user")
    public BaseMsgDTO<?> getAllUser(){
        List<Users> users = usersService.getAllUser();
        return BaseMsgDTO.success(users);
    }
    
    @GetMapping("/user-data/{userId}")
    public BaseMsgDTO<?> getUserDataInMyPage(@PathVariable Long userId){
        UserDataDTO userDataEntity = usersService.getDataUserInMyPage(userId);
        return BaseMsgDTO.success(userDataEntity);
    }


    @GetMapping("/user-data-device/{deviceId}")
    public BaseMsgDTO<?> getUserDataInMyPage(@PathVariable String deviceId){
        UserDataDTO userDataEntity = usersService.getDataUserInMyPageWithDevice(deviceId);
        return BaseMsgDTO.success(userDataEntity);
    }

    @PostMapping("/user-setting")
    public BaseMsgDTO<?> setting(HttpServletRequest request, @RequestBody UserSettingDTO data){
        setUserInfo(request);
        usersService.saveSettingData(data);
        return BaseMsgDTO.success("");
    }

}
