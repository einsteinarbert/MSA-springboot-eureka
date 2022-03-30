package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.domain.entity.JewelResultEntity;
import com.bunbusoft.ayakashi.service.dto.object.SearchFormDTO;
import com.bunbusoft.ayakashi.service.dto.paged.PageResultDTO;

public interface ProductManagerService {
    PageResultDTO<JewelResultEntity> searchPagination(SearchFormDTO filter);

}
