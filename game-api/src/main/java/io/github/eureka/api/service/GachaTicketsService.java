package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.GachaTicketsDTO;

import java.util.List;

public interface GachaTicketsService {
	List<GachaTicketsDTO> getAllGachaForSpin();

	GachaTicketsDTO getGachaTicketById(Long id);
}
