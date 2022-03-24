package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.domain.Clients;
import com.bunbusoft.ayakashi.service.dto.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.ClientssDTO;
import com.bunbusoft.ayakashi.service.dto.NewClientForm;
import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClientsService {
    List<ClientssDTO> searchClients(ClientsDTO searchForm);
    String createOrUpdateClient(NewClientForm form, Model model) throws IOException, SQLException;
}
