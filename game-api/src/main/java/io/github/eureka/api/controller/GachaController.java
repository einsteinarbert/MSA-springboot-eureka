package io.github.eureka.api.controller;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.GachaResultDTO;
import io.github.eureka.api.model.dto.GachasDTO;
import io.github.eureka.api.model.dto.SpinGachaDTO;
import io.github.eureka.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GachaController {
	private final GachasService gachasService;
	
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
}
