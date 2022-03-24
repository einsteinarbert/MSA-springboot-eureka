package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Platforms;
import com.bunbusoft.ayakashi.repository.PlatformsRepository;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.dto.object.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.object.NewPlatform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "pages/platform-manager")
public class PlatformsController {
    private PlatformsService platformsService;
    private PlatformsRepository platformsRepository;

    public PlatformsController(PlatformsService platformsService, PlatformsRepository platformsRepository) {
        this.platformsService = platformsService;
        this.platformsRepository = platformsRepository;
    }

    @ModelAttribute("newPlatform")
    public NewPlatform newClientFormDTO() {
        return new NewPlatform();
    }

    @GetMapping("/search-platform")
    public String searchClient(@ModelAttribute("searchForm")@Valid ClientsDTO searchForm,
                               @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model){
//        model.addAttribute("client", platformsService.searchClients(searchForm));

        model.addAttribute("data", platformsService.searchPlatform(searchForm, pageNumber));
        model.addAttribute("currentPage", pageNumber);
        return "pages/platforms-manager/search-form";
    }

    @GetMapping("/add-platform")
    public String displayAddClientPage(){
        return "pages/platforms-manager/add-form";
    }
    @PostMapping("/add-platform")
    public String addNewClient(@ModelAttribute("newClientForm") @Valid NewPlatform form, BindingResult result, Model model) throws SQLException, IOException {
        if (result.hasErrors()) {
            return "pages/platforms-manager/add-form";
        }
        return platformsService.createOrUpdatePlatform(form, model);
    }
    @GetMapping("/edit-platform")
    public String displayEditClientPage(@RequestParam("id") Long platformId, Model model){
        Platforms platforms = platformsRepository.findById(platformId).get();
        model.addAttribute("newClientForm", platforms);
        return "pages/platforms-manager/edit-form";
    }
    @PostMapping("/edit-platform")
    public String editClient(@ModelAttribute("newClientForm") @Valid NewPlatform form, BindingResult result, Model model) throws SQLException, IOException {
        if (result.hasErrors()) {
            return "pages/platforms-manager/edit-form";
        }
        return platformsService.createOrUpdatePlatform(form, model);
    }
}
