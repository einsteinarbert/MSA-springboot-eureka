package com.bunbusoft.ayakashi.rest;

import com.bunbusoft.ayakashi.domain.entity.JewelResultEntity;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.ProductManagerService;
import com.bunbusoft.ayakashi.service.dto.object.ClientssDTO;
import com.bunbusoft.ayakashi.service.dto.object.SearchFormDTO;
import com.bunbusoft.ayakashi.service.dto.paged.PageResultDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class WalletRestful {
    private final ProductManagerService productManagerService;
    private final PlatformsService platformsService;
    @PostMapping(value="/api/search-jewel")
    public PageResultDTO<JewelResultEntity> search(@RequestBody SearchFormDTO filter) {
        return productManagerService.searchPagination(filter);
    }

//    @PostMapping("pages/platform-manager/search-platform")
//    public PageResultDTO<ClientssDTO> searchPlatform(@RequestBody SearchFormDTO filter) {
//        return  platformsService.searchPlatform(filter);
//    }
}
