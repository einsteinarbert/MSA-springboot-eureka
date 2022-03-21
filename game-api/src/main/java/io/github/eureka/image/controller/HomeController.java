package io.github.eureka.image.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController {
	private final Environment env;

	@RequestMapping("/checking")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of image service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Image Service running at port: " + env.getProperty("local.server.port");
	}

	/**
	 * Api for test auth
	 * @return pong
	 */
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}
