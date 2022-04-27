package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.form.PurchaseForm;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.SaleInfoForm;
import jp.co.mindshift.ayakashi.api.service.ItemService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HomeController extends BaseController {
	private final ItemService itemService;


	@PostMapping("buy-product")
	public ResponseDTO<String> buyProduct(HttpServletRequest request, @RequestBody SaleInfoForm info) {
		setUserInfo(request);
		boolean res = itemService.buyProduct(info);
		String result = res ? ResponseDTO.OK : ResponseDTO.NG;
		return ResponseDTO.<String>builder().data(result).build();
	}

	@PostMapping("purchase-product")
	public ResponseDTO<String> byCashBuy(HttpServletRequest request, @RequestBody PurchaseForm info) throws JSONException, IOException, IllegalAccessException {
		setUserInfo(request);
		var res = itemService.purchase(info);
		String result = res ? ResponseDTO.OK : ResponseDTO.NG;
		return ResponseDTO.<String>builder().data(result).build();
	}

	@GetMapping("list-products")
	public ResponseDTO<?> getListProducts(
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "productType", required = false) String productType,
			@RequestParam(name = "itemType", required = false) String itemType
	) {
		return ResponseDTO.builder().data(itemService.getListProducts(productId, productType, itemType)).build();
	}
}
