package io.github.eureka.api.controller;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.PurchaseDTO;
import io.github.eureka.api.model.dto.SaleInfoDTO;
import io.github.eureka.api.service.ItemService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController extends BaseController {
	private final Environment env;
	private final ItemService itemService;

	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of api service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Image Service running at port: " + env.getProperty("local.server.port");
	}

	/**
	 * Api for test auth
	 */
	@PersistenceContext
	private final EntityManager em;
	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		Users usr = (Users) em.createNativeQuery("select * from users limit 1", Users.class).getSingleResult();
		return ResponseEntity.ok(usr);
	}

	@PostMapping("buy-product")
	public BaseMsgDTO<String> buyProduct(HttpServletRequest request, @RequestBody SaleInfoDTO info) {
		setUserInfo(request);
		boolean res = itemService.buyProduct(info);
		String result = res ? BaseMsgDTO.OK : BaseMsgDTO.NG;
		return BaseMsgDTO.<String>builder().data(result).build();
	}

	@PostMapping("purchase-product")
	public BaseMsgDTO<String> byCashBuy(HttpServletRequest request, @RequestBody PurchaseDTO info) throws JSONException, IOException, IllegalAccessException {
		setUserInfo(request);
		var res = itemService.purchase(info);
		String result = res ? BaseMsgDTO.OK : BaseMsgDTO.NG;
		return BaseMsgDTO.<String>builder().data(result).build();
	}

	@GetMapping("list-products")
	public BaseMsgDTO<?> getListProducts(
			@RequestParam(name = "productId", required = false) Long productId,
			@RequestParam(name = "productType", required = false) String productType,
			@RequestParam(name = "itemType", required = false) String itemType
	) {
		return BaseMsgDTO.builder().data(itemService.getListProducts(productId, productType, itemType)).build();
	}
}
