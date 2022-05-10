package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;

public interface QuestsService {
	ResponseDTO<?> getList(Integer type);

	ResponseDTO<?> clearQuest(ClearQuestForm clearQuestForm);
}
