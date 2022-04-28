package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;
import jp.co.mindshift.ayakashi.api.repo.QuestsRepository;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.QuestsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestsServiceImpl extends BaseService implements QuestsService {
	private final QuestsRepository questsRepository;
	@Override
	public ResponseDTO<?> getList() {
		return ResponseDTO.success(questsRepository.findAll());
	}

	@Override
	public ResponseDTO<?> clearQuest(ClearQuestForm clearQuestForm) {		
		return ResponseDTO.success();
	}
}
