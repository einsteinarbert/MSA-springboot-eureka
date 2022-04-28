package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.repo.SpotsRepository;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.SpotsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpotsServiceImpl extends BaseService implements SpotsService {
	private final SpotsRepository spotsRepository;

	@Override
	public ResponseDTO<?> getList() {
		return ResponseDTO.success(spotsRepository.findAll());
	}
}
