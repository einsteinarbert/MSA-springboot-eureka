package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.DataUtil;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.GachaTickets;
import io.github.eureka.api.model.dto.GachaTicketsDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;
import io.github.eureka.api.repo.GachaTicketsRepository;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.GachaTicketsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GachaTicketsServiceImpl extends BaseService implements GachaTicketsService {
	private final GachaTicketsRepository gachaTicketsRepository;
	
	@Override
	public List<GachaTicketsDTO> getAllGachaForSpin() {
		return super.mapList(gachaTicketsRepository.findAll(), GachaTicketsDTO.class);
	}

	@Override
	public GachaTicketsDTO getGachaTicketById(Long id) {
		GachaTickets gachaExists = gachaTicketsRepository.getById(id);
		if (DataUtil.isNullOrEmpty(gachaExists)){
			throw new IllegalArgumentException(MsgUtil.getMessage("gacha.notfound"));
		}
		return super.map(gachaExists, GachaTicketsDTO.class);
	}
}
