package io.github.eureka.api.service;

import io.github.eureka.api.model.UserItems;
import io.github.eureka.api.model.dto.GachaResultDTO;
import io.github.eureka.api.model.dto.GachasDTO;
import io.github.eureka.api.model.dto.SpinGachaDTO;
import io.github.eureka.api.model.dto.UserItemsDTO;

import java.util.List;

public interface GachasService {
	List<GachasDTO> getAllGachaForSpin();

	GachasDTO getGachaTicketById(Long id);

	GachaResultDTO spinGacha(SpinGachaDTO spinGachaDTO);

	Boolean saveBonusGacha(UserItemsDTO userItemsDTO) throws IllegalAccessException;
}
