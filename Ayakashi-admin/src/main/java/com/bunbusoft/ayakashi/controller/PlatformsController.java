package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Platforms;
import com.bunbusoft.ayakashi.repository.PlatformsRepository;
import com.bunbusoft.ayakashi.securities.FieldMatch;
import com.bunbusoft.ayakashi.service.PlatformsService;
import com.bunbusoft.ayakashi.service.dto.object.*;
import com.bunbusoft.ayakashi.utils.DataUtil;
import com.bunbusoft.ayakashi.view.RowFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping(value = "pages/platform-manager")
@Slf4j
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

//    @ModelAttribute("rowFilter")
//    public List<RowFilter> inIntScreen() {
//        Map<String, String> fieldMap = new HashMap<>();
//        fieldMap.put("pl.id", "ID");
//        fieldMap.put("pl.platform_token", "プラットホームID");
//        fieldMap.put("pl.name", "プラットホーム名");
//        fieldMap.put("pl.type", "種別");
//        return ViewUtils.getFormCondition(fieldMap);
//    }
    @ModelAttribute("filterWrapper")
    public FilterWrapperDTO filterDTOWrapper() {
        return new FilterWrapperDTO();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldMatch(first = "rowFilter", second = "", message = "")
    static class FormFilter {
        private List<RowFilter> rowFilter;
    }

    /**
     * get view
     * @return jewel-manager.html
     */
//    @RequestMapping( path = {"/search-platform"})
//    public String displayPlatformScreen(Model model) {
//        return "pages/platforms-manager/search-form"; // view
//    }


    @GetMapping("/add-platform")
    public String displayAddClientPage(){
        return "pages/platforms-manager/add-form";
    }

    @PostMapping("/add-platform")
    public String addNewClient(@ModelAttribute("newPlatform") @Valid NewPlatform form, BindingResult result, Model model) throws SQLException, IOException {
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

    @GetMapping("/search-platform")
    public String searchPlatform(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model){
        //Lan dau build khoi tao gia tri la rong
        FilterWrapperDTO searchForm = new FilterWrapperDTO();
        ArrayList<FilterDTO> arr = new ArrayList<>();
        if(DataUtil.isNullOrEmpty(searchForm.getFilterList())){
            FilterDTO attr = new FilterDTO(null, null, null);
            arr.add(attr);
            searchForm.setFilterList(arr);
        }
        model.addAttribute("filterWrapper", searchForm);
        model.addAttribute("data", platformsService.searchPlatform(null, pageNumber));
        model.addAttribute("currentPage", pageNumber);
        return "pages/platforms-manager/search-form";
    }

    @PostMapping("/search-platform")
    public String searchPlatform(@ModelAttribute FilterWrapperDTO filterWrapper,
                                 @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model){
        //Khi user xoá search thì khoi tao lai gia tri
        ArrayList<FilterDTO> arr = new ArrayList<>();
        if(DataUtil.isNullOrEmpty(filterWrapper.getFilterList())){
            FilterDTO attr = new FilterDTO(null, null, null);
            arr.add(attr);
            filterWrapper.setFilterList(arr);
        }else{
            //TH gui len list filter bi null thi xoa cac ban ghi null de set lai
            ArrayList<FilterDTO> lstFilter = filterWrapper.getFilterList();
            Set<FilterDTO> set = new HashSet<>(lstFilter);
            lstFilter.clear();
            lstFilter.addAll(set);
            for(int i=0; i< lstFilter.size();i++){
                if(DataUtil.isNullOrEmpty(lstFilter.get(i).getField()) & DataUtil.isNullOrEmpty(lstFilter.get(i).getCondition())
                        && DataUtil.isNullOrEmpty(lstFilter.get(i).getKeyword())){
                    lstFilter.remove(i);
                }
            }
            filterWrapper.setFilterList(lstFilter);
        }
        if(DataUtil.isNullOrEmpty(filterWrapper.getFilterList())){
            FilterDTO attr = new FilterDTO(null, null, null);
            arr.add(attr);
            filterWrapper.setFilterList(arr);
        }
        model.addAttribute("data", platformsService.searchPlatform(filterWrapper, pageNumber));
        model.addAttribute("filterWrapper", filterWrapper);
        model.addAttribute("currentPage", pageNumber);
        return "pages/platforms-manager/search-form";
    }

}
