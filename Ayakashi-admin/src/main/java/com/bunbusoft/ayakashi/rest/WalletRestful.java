package com.bunbusoft.ayakashi.rest;

import com.bunbusoft.ayakashi.domain.entity.JewelResultEntity;
import com.bunbusoft.ayakashi.service.ProductManagerService;
import com.bunbusoft.ayakashi.service.dto.object.JewelFormDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class WalletRestful {
    private final ProductManagerService productManagerService;

    @PostMapping(value="/api/search-jewel")
    public Page<JewelResultEntity> search(@RequestBody JewelFormDTO filter) {
        log.info("/api/search-jewel \n{}", filter);

        return productManagerService.searchPagination(filter);
    }

    @GetMapping("/api/ping")
    public String ping () {
        return "pong";
    }

}
