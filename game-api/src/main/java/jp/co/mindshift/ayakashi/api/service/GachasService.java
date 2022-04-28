package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.form.GachasForm;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.model.form.UserItemsForm;

import java.util.List;

public interface GachasService {
	List<GachasForm> getAllGachaForSpin();

	GachasForm getGachaTicketById(Long id);

	ResponseDTO<?> spinGacha(SpinGachaForm spinGachaForm) throws IllegalAccessException;
}
