package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.form.PuzzleGameForm;

public interface PuzzleGameService {
	ResponseDTO<?> endGameProcess(PuzzleGameForm puzzleGameForm);
}
