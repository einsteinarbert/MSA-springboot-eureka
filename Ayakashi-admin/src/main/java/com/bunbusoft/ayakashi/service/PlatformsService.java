package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.ClientssDTO;
import com.bunbusoft.ayakashi.service.dto.NewPlatform;
import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PlatformsService {
    List<ClientssDTO> searchPlatform(ClientsDTO searchForm);
    String createOrUpdatePlatform(NewPlatform form, Model model) throws IOException, SQLException;
}
