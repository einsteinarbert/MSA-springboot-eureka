package io.github.eureka.api.controller;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.form.PuzzleGameForm;
import io.github.eureka.api.service.PuzzleGameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PuzzleGameController {
	@Autowired
	PuzzleGameService puzzleGameService;

	@PostMapping("/end-game")
	public ResponseDTO<?> updateUser(@Valid @RequestBody PuzzleGameForm puzzleGameForm){
		return puzzleGameService.endGameProcess(puzzleGameForm);
	}
}
