package io.github.eureka.api.controller;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.SaleInfoDTO;
import io.github.eureka.api.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

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
	 * @return Test EM di
	 */
	@PersistenceContext
	private final EntityManager em;
	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		Users usr = (Users) em.createNativeQuery("select * from users limit 1", Users.class).getSingleResult();
		return ResponseEntity.ok(usr);
	}

	@PostMapping("buy-product")
	public ResponseEntity<?> buyItems(HttpServletRequest request, @RequestBody SaleInfoDTO info) {
		setUserInfo(request);
		itemService.buyItem(info);
		return ResponseEntity.ok().build();
	}
}
