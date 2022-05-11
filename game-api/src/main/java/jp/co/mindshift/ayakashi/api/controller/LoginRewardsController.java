package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.service.LoginRewardsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login-rewards")
public class LoginRewardsController {
	private final LoginRewardsService loginRewardsService;

	@GetMapping("/list/{userId}")
	public ResponseDTO<?> getListLoginRewards(@PathVariable Long userId) {
		return loginRewardsService.getList(userId);
	}
}
