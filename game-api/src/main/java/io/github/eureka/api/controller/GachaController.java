package io.github.eureka.api.controller;

import io.github.eureka.api.model.UserItems;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.repo.UserItemsRepository;
import io.github.eureka.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GachaController {
	private final GachasService gachasService;
	private final UserItemsRepository userItemsRepository;
	
	@GetMapping("/gacha-list")
	public BaseMsgDTO<List<GachasDTO>> getTicket(){
		List<GachasDTO> tickets = gachasService.getAllGachaForSpin();
		return BaseMsgDTO.success(tickets);
	}
	@GetMapping("/gacha/{id}")
	public BaseMsgDTO<GachasDTO> getDetailGachaTicket(@PathVariable Long id){
		GachasDTO tickets = gachasService.getGachaTicketById(id);
		return BaseMsgDTO.success(tickets);
	}
	
	@PostMapping("/gacha-result")
	public BaseMsgDTO<GachaResultDTO> spinGacha(@RequestBody SpinGachaDTO spinGachaDTO) {
		GachaResultDTO resultSpin = gachasService.spinGacha(spinGachaDTO);
		return BaseMsgDTO.success(resultSpin);
	}

	@PostMapping("/save-bonus-gacha")
	public BaseMsgDTO<?> saveBonusGacha(@RequestBody UserItemsDTO userItemsDTO) {
		Boolean isNextLevel = gachasService.saveBonusGacha(userItemsDTO);
		UserItems items = userItemsRepository.findUserItemsByUserIdAndItemId(userItemsDTO.getUserId(), userItemsDTO.getItemId());
		if(isNextLevel){
			return BaseMsgDTO.success(items, 10000);
		}else{
			return BaseMsgDTO.success(items);
		}
	}
}
