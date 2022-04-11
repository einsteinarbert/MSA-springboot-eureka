package io.github.eureka.api.controller;

import io.github.eureka.api.model.GachaTickets;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.GachaTicketsDTO;
import io.github.eureka.api.service.GachaTicketsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GachaController {
	private final GachaTicketsService gachaTicketsService;
	
	@GetMapping("/gacha-list")
	public BaseMsgDTO<List<GachaTicketsDTO>> getTicket(){
		List<GachaTicketsDTO> tickets = gachaTicketsService.getAllGachaForSpin();
		return BaseMsgDTO.success(tickets);
	}
	@GetMapping("/gacha/{id}")
	public BaseMsgDTO<GachaTicketsDTO> getDetailGachaTicket(@PathVariable Long id){
		GachaTicketsDTO tickets = gachaTicketsService.getGachaTicketById(id);
		return BaseMsgDTO.success(tickets);
	}
}
