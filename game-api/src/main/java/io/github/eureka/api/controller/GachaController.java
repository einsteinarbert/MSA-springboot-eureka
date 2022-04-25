package io.github.eureka.api.controller;

import io.github.eureka.api.model.UserItems;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.repo.UserItemsRepository;
import io.github.eureka.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GachaController {
	private final GachasService gachasService;
	private final UserItemsRepository userItemsRepository;
	
	@GetMapping("/gacha-list")
	public ResponseDTO<List<GachasDTO>> getGacha(){
		List<GachasDTO> tickets = gachasService.getAllGachaForSpin();
		return ResponseDTO.success(tickets);
	}
	@GetMapping("/gacha/{id}")
	public ResponseDTO<GachasDTO> getDetailGachaTicket(@PathVariable Long id){
		GachasDTO tickets = gachasService.getGachaTicketById(id);
		return ResponseDTO.success(tickets);
	}
	
	@PostMapping("/gacha-result")
	public ResponseDTO<GachaResultDTO> spinGacha(@RequestBody SpinGachaDTO spinGachaDTO) {
		GachaResultDTO resultSpin = gachasService.spinGacha(spinGachaDTO);
		return ResponseDTO.success(resultSpin);
	}

	@PostMapping("/save-bonus-gacha")
	public ResponseDTO<?> saveBonusGacha(@RequestBody UserItemsDTO userItemsDTO) throws IllegalAccessException {
		Boolean isNextLevel = gachasService.saveBonusGacha(userItemsDTO);
		UserItems items = userItemsRepository.findUserItemsByUserIdAndItemId(userItemsDTO.getUserId(), userItemsDTO.getItemId());
		if(isNextLevel){
			return ResponseDTO.success(items, 10000);
		} else {
			return ResponseDTO.success(items);
		}
	}
}
