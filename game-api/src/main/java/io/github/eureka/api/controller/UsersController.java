package io.github.eureka.api.controller;

import io.github.eureka.api.model.Users;
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
        usersService.createUser(users);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/edit")
    public ResponseEntity<?> updateUser(@RequestBody Users users){
        usersService.updateUser(users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/get/{id}")
    public ResponseEntity<?> getOneUser(@RequestParam Long id){
        Users users = usersService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @GetMapping("users/get")
    public ResponseEntity<?> getAllUser(){

        return ResponseEntity.ok().build();
    }


}
