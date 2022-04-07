package io.github.eureka.api.controller;

import io.github.eureka.api.common.DataUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;
import io.github.eureka.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody Users users){
        if(DataUtil.isNullOrEmpty(users.getId())) {
            usersService.createUser(users);
        }else{
            usersService.updateUser(users);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/change-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody ChangePasswordDTO users){
        usersService.updateUserPassword(users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/get/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        Users users = usersService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping("users/get")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok().build();
    }


}
