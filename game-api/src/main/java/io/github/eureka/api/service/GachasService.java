package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.GachaCharactersDTO;
import io.github.eureka.api.model.dto.GachasDTO;
import io.github.eureka.api.model.dto.SpinGachaDTO;

import java.util.List;

public interface GachasService {
	List<GachasDTO> getAllGachaForSpin();

	GachasDTO getGachaTicketById(Long id);

	GachaCharactersDTO spinGacha(SpinGachaDTO spinGachaDTO);
}
