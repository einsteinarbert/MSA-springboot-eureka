package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.PuzzleGameForm;

public interface PuzzleGameService {
	ResponseDTO<?> endGameProcess(PuzzleGameForm puzzleGameForm);
}
