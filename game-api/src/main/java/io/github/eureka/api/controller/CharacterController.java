package io.github.eureka.api.controller;

import io.github.eureka.api.model.Characters;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.service.CharactersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CharacterController {
    private final CharactersService charactersService;
    @GetMapping("characters/{userId}")
    public ResponseEntity<?> getCharacterOfUser(@RequestParam Long userId){
        Characters characters = charactersService.getCharacterByUser(userId);
        return ResponseEntity.ok(characters);
    }
}
