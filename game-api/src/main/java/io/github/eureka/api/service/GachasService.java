package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.GachasDTO;
import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.form.SpinGachaForm;
import io.github.eureka.api.model.dto.UserItemsDTO;

import java.util.List;

public interface GachasService {
	List<GachasDTO> getAllGachaForSpin();

	GachasDTO getGachaTicketById(Long id);

	ResponseDTO<?> spinGacha(SpinGachaForm spinGachaForm);

	Boolean saveBonusGacha(UserItemsDTO userItemsDTO) throws IllegalAccessException;
}
