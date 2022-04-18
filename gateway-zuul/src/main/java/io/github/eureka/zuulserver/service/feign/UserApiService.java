package io.github.eureka.zuulserver.service.feign;

import io.github.eureka.zuulserver.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 16/04/2022<br/>
 */
@ReactiveFeignClient(name = "${feign.address.game-api.name}", url = "${feign.address.game-api.url}")
public interface UserApiService {
    @PostMapping(value = "/api/users/create")
    Mono<String> createNewUser(@RequestBody Users users);
}