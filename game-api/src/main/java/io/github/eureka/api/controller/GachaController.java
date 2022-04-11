package io.github.eureka.api.controller;

import io.github.eureka.api.model.GachaTickets;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.BaseMsgDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GachaController {
	private final GachaTicketsService gachaTicketsService;
	@GetMapping("users/gacha-list")
	public BaseMsgDTO<List<GachaTicketsDTO>> getTicket(@PathVariable Long id){
		BaseMsgDTO<GachaTicketsDTO> tickets = gachaTicketsService.getAllGacha(id);
		return tickets;
	}
}
