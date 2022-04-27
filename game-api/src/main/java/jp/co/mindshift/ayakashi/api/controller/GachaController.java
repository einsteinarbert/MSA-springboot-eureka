package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.UserItems;
import jp.co.mindshift.ayakashi.api.model.dto.*;
import jp.co.mindshift.ayakashi.api.model.dto.GachasDTO;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.repo.UserItemsRepository;
import jp.co.mindshift.ayakashi.api.service.GachasService;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.dto.UserItemsDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
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
	public ResponseDTO<?> spinGacha(@Valid @RequestBody SpinGachaForm spinGachaForm) {
		return gachasService.spinGacha(spinGachaForm);
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
