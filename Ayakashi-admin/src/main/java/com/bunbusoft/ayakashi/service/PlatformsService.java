package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.object.*;
import com.bunbusoft.ayakashi.service.dto.paged.PageableCustom;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PlatformsService {
    Page<ClientssDTO> searchPlatform(FilterDTOWrapper searchForm, int pageNumber);
    String createOrUpdatePlatform(NewPlatform form, Model model) throws IOException, SQLException;
}
