package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Platforms;
import com.bunbusoft.ayakashi.repository.PlatformsRepository;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.dto.object.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.object.FilterDTOWrapper;
import com.bunbusoft.ayakashi.service.dto.object.FilterPlatformDTO;
import com.bunbusoft.ayakashi.service.dto.object.NewPlatform;
import com.bunbusoft.ayakashi.utils.DataUtil;
import com.bunbusoft.ayakashi.view.JewelState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Filter;

@Controller
@RequestMapping(value = "pages/platform-manager")
@Slf4j
@Scope("request")
public class PlatformsController {
    private PlatformsService platformsService;
    private PlatformsRepository platformsRepository;

    public PlatformsController(PlatformsService platformsService, PlatformsRepository platformsRepository) {
        this.platformsService = platformsService;
        this.platformsRepository = platformsRepository;
    }

    @ModelAttribute("newPlatform")
    public NewPlatform newPlatformDTO() {
        return new NewPlatform();
    }

    @ModelAttribute("filterPlatformDTO")
    public FilterPlatformDTO filterPlatformDTO() {
        return new FilterPlatformDTO();
    }

    @ModelAttribute("filterDTOWrapper")
    public FilterDTOWrapper filterDTOWrapper() {
        return new FilterDTOWrapper();
    }

    @PostMapping("/search-platform")
    public String searchClient(@ModelAttribute("filterDTOWrapper")@Valid FilterDTOWrapper searchForm,
                               @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model){
        //Lan dau build thi cho no 1 gia tri
        ArrayList<FilterPlatformDTO> arr = new ArrayList<>();
        if(DataUtil.isNullOrEmpty(searchForm.getClientList())){
            FilterPlatformDTO attr = new FilterPlatformDTO(null, null, null);
            arr.add(attr);
            searchForm.setClientList(arr);
        }
        model.addAttribute("filterPlatformDTO", searchForm);
        model.addAttribute("data", platformsService.searchPlatform(searchForm, pageNumber));
        model.addAttribute("currentPage", pageNumber);
        return "pages/platforms-manager/search-form";
    }

    @GetMapping("/search-platform")
    public String searchPlatform(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model){
        model.addAttribute("filterPlatformDTO", new ArrayList<FilterDTOWrapper>());
        model.addAttribute("data", platformsService.searchPlatform(null, pageNumber));
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
