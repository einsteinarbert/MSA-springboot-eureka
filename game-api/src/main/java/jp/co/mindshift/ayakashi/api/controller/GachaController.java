package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.UserItems;
import jp.co.mindshift.ayakashi.api.model.form.GachasForm;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.repo.UserItemsRepository;
import jp.co.mindshift.ayakashi.api.service.GachasService;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.UserItemsForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/gacha")
public class GachaController {
	private final GachasService gachasService;
	private final UserItemsRepository userItemsRepository;
	
	@GetMapping("/list")
	public ResponseDTO<List<GachasForm>> getGacha(){
		List<GachasForm> tickets = gachasService.getAllGachaForSpin();
		return ResponseDTO.success(tickets);
	}
	@GetMapping("/{id}")
	public ResponseDTO<GachasForm> getDetailGachaTicket(@PathVariable Long id){
		GachasForm tickets = gachasService.getGachaTicketById(id);
		return ResponseDTO.success(tickets);
	}
	
	@PostMapping("/result")
	public ResponseDTO<?> spinGacha(@Valid @RequestBody SpinGachaForm spinGachaForm) {
		return gachasService.spinGacha(spinGachaForm);
	}

	@PostMapping("/save-bonus")
	public ResponseDTO<?> saveBonusGacha(@RequestBody UserItemsForm userItemsForm) throws IllegalAccessException {
		Boolean isNextLevel = gachasService.saveBonusGacha(userItemsForm);
		UserItems items = userItemsRepository.findUserItemsByUserIdAndItemId(userItemsForm.getUserId(), userItemsForm.getItemId());
		if(isNextLevel){
			return ResponseDTO.success(items, 10000);
		} else {
			return ResponseDTO.success(items);
		}
	}
}
