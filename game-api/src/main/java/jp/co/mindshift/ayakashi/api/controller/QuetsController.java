package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;
import jp.co.mindshift.ayakashi.api.service.QuestsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("quest")
public class QuetsController {
	private final QuestsService questsService;


	@GetMapping("/list")
	public ResponseDTO<?> getListSpots(){
		return questsService.getList();
	}
	
	@PostMapping("/clear")
	public ResponseDTO<?> getListSpots(@RequestBody ClearQuestForm clearQuestForm){
		return questsService.clearQuest(clearQuestForm);
	}
}
