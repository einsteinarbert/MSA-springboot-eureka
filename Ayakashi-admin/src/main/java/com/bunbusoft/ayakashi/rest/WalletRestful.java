package com.bunbusoft.ayakashi.rest;

import com.bunbusoft.ayakashi.service.dto.FilterJewelDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WalletRestful {

    @PostMapping(value="/api/search-jewel")
    public String search(@RequestBody List<FilterJewelDTO> filter) {
        log.info("HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n{}", filter);
        return "successfully";
    }

    @GetMapping("/api/ping")
    public String ping () {
        return "pong";
    }

}
