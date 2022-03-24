package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Clients;
import com.bunbusoft.ayakashi.repository.ClientsRepository;
import com.bunbusoft.ayakashi.service.ClientsService;
import com.bunbusoft.ayakashi.service.dto.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.ClientssDTO;
import com.bunbusoft.ayakashi.service.dto.NewClientForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "pages/client-manager")
public class ClientsController {
    private ClientsService clientsService;
    private ClientsRepository clientsRepository;

    public ClientsController(ClientsService clientsService, ClientsRepository clientsRepository) {
        this.clientsService = clientsService;
        this.clientsRepository = clientsRepository;
    }

    @ModelAttribute("newClientForm")
    public NewClientForm newClientFormDTO() {
        return new NewClientForm();
    }

    @GetMapping("/search-client")
    public String searchClient(@ModelAttribute("searchForm")@Valid ClientsDTO searchForm, Model model){
//        model.addAttribute("client", clientsService.searchClients(searchForm));
        model.addAttribute("clients", clientsService.searchClients(searchForm));
        return "pages/client-manager/search-form";
    }

    @GetMapping("/add-client")
    public String displayAddClientPage(){
        return "pages/client-manager/add-form";
    }
    @PostMapping("/add-client")
    public String addNewClient(@ModelAttribute("newClientForm") @Valid NewClientForm form, BindingResult result, Model model) throws SQLException, IOException {
        if (result.hasErrors()) {
            return "pages/client-manager/add-form";
        }
        return clientsService.createOrUpdateClient(form, model);
    }
    @GetMapping("/edit-client")
    public String displayEditClientPage(@RequestParam("id") Long clientId, Model model){
        Clients clients = clientsRepository.findById(clientId).get();
        model.addAttribute("newClientForm", clients);
        return "pages/client-manager/edit-form";
    }
    @PostMapping("/edit-client")
    public String editClient(@ModelAttribute("newClientForm") @Valid NewClientForm form, BindingResult result, Model model) throws SQLException, IOException {
        if (result.hasErrors()) {
            return "pages/client-manager/edit-form";
        }
        return clientsService.createOrUpdateClient(form, model);
    }
}
