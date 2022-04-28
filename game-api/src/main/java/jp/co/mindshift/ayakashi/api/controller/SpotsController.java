package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.service.SpotsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("spots")
public class SpotsController {
	private final SpotsService spotsService;
	
	@GetMapping("/list")
	public ResponseDTO<?> getListSpots(){
		return spotsService.getList();
	}
}
