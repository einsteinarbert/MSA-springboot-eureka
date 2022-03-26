package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.domain.entity.JewelResultEntity;
import com.bunbusoft.ayakashi.service.dto.object.JewelFormDTO;
import org.springframework.data.domain.Page;

public interface ProductManagerService {
    Page<JewelResultEntity> searchPagination(JewelFormDTO filter);

}
