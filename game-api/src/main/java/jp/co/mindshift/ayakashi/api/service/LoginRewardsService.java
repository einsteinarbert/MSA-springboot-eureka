package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;

public interface LoginRewardsService {
	ResponseDTO<?> getList(Long userId);
}