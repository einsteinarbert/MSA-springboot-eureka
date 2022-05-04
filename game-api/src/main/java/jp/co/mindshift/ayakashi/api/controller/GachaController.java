package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.GachasForm;
import jp.co.mindshift.ayakashi.api.model.form.SpinGachaForm;
import jp.co.mindshift.ayakashi.api.repo.UserItemsRepository;
import jp.co.mindshift.ayakashi.api.service.GachasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/gacha")
public class GachaController {
	private final GachasService gachasService;

	@GetMapping("/list")
	public ResponseDTO<List<GachasForm>> getGacha() {
		List<GachasForm> tickets = gachasService.getAllGachaForSpin();
		return ResponseDTO.success(tickets);
	}

	@GetMapping("/{id}")
	public ResponseDTO<GachasForm> getDetailGachaTicket(@PathVariable Long id) {
		GachasForm tickets = gachasService.getGachaTicketById(id);
		return ResponseDTO.success(tickets);
	}

	@PostMapping("/result")
	public ResponseDTO<?> spinGacha(@Valid @RequestBody SpinGachaForm spinGachaForm) throws IllegalAccessException {
		return gachasService.spinGacha(spinGachaForm);
	}

}
