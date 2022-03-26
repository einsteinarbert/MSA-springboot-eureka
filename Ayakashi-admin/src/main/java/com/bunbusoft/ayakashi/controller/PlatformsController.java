package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Platforms;
import com.bunbusoft.ayakashi.repository.PlatformsRepository;
import com.bunbusoft.ayakashi.securities.FieldMatch;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.dto.object.ClientsDTO;
import com.bunbusoft.ayakashi.service.dto.object.NewPlatform;
import com.bunbusoft.ayakashi.view.JewelState;
import com.bunbusoft.ayakashi.view.Option;
import com.bunbusoft.ayakashi.view.RowFilter;
import com.bunbusoft.ayakashi.view.Selector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "pages/platform-manager")
@Slf4j
@Scope("request")
public class PlatformsController {
    private PlatformsService platformsService;
    private PlatformsRepository platformsRepository;
    @Autowired
    private JewelState state;

    public PlatformsController(PlatformsService platformsService, PlatformsRepository platformsRepository) {
        this.platformsService = platformsService;
        this.platformsRepository = platformsRepository;
    }

    @ModelAttribute("newPlatform")
    public NewPlatform newClientFormDTO() {
        return new NewPlatform();
    }

    @ModelAttribute("rowFilter")
    public List<RowFilter> inIntScreen(Model model) {
        List<RowFilter> list = (List<RowFilter>) model.getAttribute("rowFilter");
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
            List<Option> lst = Arrays.asList(
                    new Option("field1", 1),
                    new Option("field2 longly loooog", 2),
                    new Option("field3", 3)
            );
            Selector field = Selector.builder()
                    .showLabel(false)
                    .showToolTip(true)
                    .toolTip("フィールド")
                    .options(lst)
                    .build();
            RowFilter row = RowFilter.builder()
                    .isDefault(true)
                    .selectors(new ArrayList<>())
                    .build();
            row.getSelectors().add(field);
            List<Option> operators = Arrays.asList(
                    new Option("=", 1),
                    new Option("LIKE", 2),
                    new Option("NOT LIKE", 3)
            );
            var conditions = Selector.builder()
                    .showLabel(false)
                    .showToolTip(true)
                    .toolTip("条件")
                    .options(operators)
                    .build();
            row.getSelectors().add(conditions);

            var keywords = Selector.builder()
                    .showLabel(false)
                    .showToolTip(true)
                    .toolTip("キーワード")
                    .options(lst)
                    .build();
            row.getSelectors().add(keywords);
            list.add(row);
        }
        state.setData(list);
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldMatch(first = "rowFilter", second = "", message = "")
    static class FormFilter {
        private List<RowFilter> rowFilter;
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
