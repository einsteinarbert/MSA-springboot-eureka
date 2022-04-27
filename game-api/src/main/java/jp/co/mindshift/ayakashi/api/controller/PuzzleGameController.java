package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.PuzzleGameForm;
import jp.co.mindshift.ayakashi.api.service.PuzzleGameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PuzzleGameController {
	@Autowired
    PuzzleGameService puzzleGameService;

	@PostMapping("/end-game")
	public ResponseDTO<?> endPuzzleGame(@Valid @RequestBody PuzzleGameForm puzzleGameForm){
		return puzzleGameService.endGameProcess(puzzleGameForm);
	}
	
	@GetMapping("/list-puzzle-item/{userId}")
	public ResponseDTO<?> getListItemPuzzleGame(@PathVariable Long userId){
		return puzzleGameService.getListItemPuzzleGame(userId);
	}
}
