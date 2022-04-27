package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.GachasDTO;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.model.dto.UserItemsDTO;

import java.util.List;

public interface GachasService {
	List<GachasDTO> getAllGachaForSpin();

	GachasDTO getGachaTicketById(Long id);

	ResponseDTO<?> spinGacha(SpinGachaForm spinGachaForm);

	Boolean saveBonusGacha(UserItemsDTO userItemsDTO) throws IllegalAccessException;
}
