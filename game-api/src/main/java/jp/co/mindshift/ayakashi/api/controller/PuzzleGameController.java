package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.PuzzleGameForm;
import jp.co.mindshift.ayakashi.api.service.PuzzleGameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("puzzle-game")
public class PuzzleGameController {
	private final PuzzleGameService puzzleGameService;

	@PostMapping("/end-game")
	public ResponseDTO<?> endPuzzleGame(@Valid @RequestBody PuzzleGameForm puzzleGameForm) {
		return puzzleGameService.endGameProcess(puzzleGameForm);
	}

	@GetMapping("/list-item/{userId}")
	public ResponseDTO<?> getListItemPuzzleGame(@PathVariable Long userId) {
		return puzzleGameService.getListItemPuzzleGame(userId);
	}
}
