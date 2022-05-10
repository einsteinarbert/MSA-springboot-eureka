package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;
import jp.co.mindshift.ayakashi.api.service.MapModeService;
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
@RequestMapping("map")
public class MapModeController extends BaseController{
	private final QuestsService questsService;
	private final MapModeService mapModeService;


	@GetMapping("/list/quest")
	public ResponseDTO<?> getListQuest(HttpServletRequest request, Integer type) {
		setUserInfo(request);
		return questsService.getList(type);
	}
	
	@PostMapping("/clear/quest")
	public ResponseDTO<?> clearQuest(@RequestBody ClearQuestForm clearQuestForm){
		return questsService.clearQuest(clearQuestForm);
	}

	@PostMapping("/list/position")
	public ResponseDTO<?> getListPanel(HttpServletRequest request, Long postId) {
		setUserInfo(request);
		return mapModeService.updatePositionMatrix(postId);
	}
}
