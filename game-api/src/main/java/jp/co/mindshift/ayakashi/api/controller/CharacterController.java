package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.Characters;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.service.CharactersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharactersService charactersService;
    
    @GetMapping("/user-char/{userId}")
    public ResponseDTO<?> getCharacterOfUser(@PathVariable Long userId){
        List<Characters> characters = charactersService.getCharacterByUser(userId);
        return ResponseDTO.success(characters);
    }

    @GetMapping("/detail/{id}")
    public ResponseDTO<?> getOneCharacter(@PathVariable Long id){
        Characters characters = charactersService.getDetailCharacter(id);
        return ResponseDTO.success(characters);
    }
}
