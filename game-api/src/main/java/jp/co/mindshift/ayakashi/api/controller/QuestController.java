package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;
import jp.co.mindshift.ayakashi.api.service.QuestsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("quest")
public class QuestController extends BaseController{
	private final QuestsService questsService;


	@GetMapping("/list")
	public ResponseDTO<?> getListQuest(HttpServletRequest request) {
		setUserInfo(request);
		return questsService.getList();
	}
	
	@PostMapping("/clear")
	public ResponseDTO<?> clearQuest(@RequestBody ClearQuestForm clearQuestForm){
		return questsService.clearQuest(clearQuestForm);
	}
}
