package io.github.eureka.api.controller;

import io.github.eureka.api.model.Characters;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.service.CharactersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CharacterController {
    private final CharactersService charactersService;
    
    @GetMapping("user-characters/{userId}")
    public BaseMsgDTO<?> getCharacterOfUser(@PathVariable Long userId){
        List<Characters> characters = charactersService.getCharacterByUser(userId);
        return BaseMsgDTO.success(characters);
    }

    @GetMapping("char-detail/{id}")
    public BaseMsgDTO<?> getOneCharacter(@PathVariable Long id){
        Characters characters = charactersService.getDetailCharacter(id);
        return BaseMsgDTO.success(characters);
    }
}
