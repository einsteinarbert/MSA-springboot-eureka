package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.securities.FieldMatch;
import com.bunbusoft.ayakashi.utils.ViewUtils;
import com.bunbusoft.ayakashi.view.RowFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "pages")
@Slf4j
@Scope("request")
public class WalletController {

    @ModelAttribute("rowFilter")
    public List<RowFilter> inIntScreen() {
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("jp.id", "ID");
        fieldMap.put("jp.client_id", "クライアント");
        fieldMap.put("jp.product_code", "商品ID");
        fieldMap.put("w.display_name", "ウォレット名");
        fieldMap.put("jp.number", "枚数");
        fieldMap.put("bonus.display_name", "ボーナス\nウォレット名");
        fieldMap.put("jp.bonus_number", "ボーナス\n枚数");
        return ViewUtils.getFormCondition(fieldMap);
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
    @RequestMapping( path = {"/jewel-manager"})
    public String jewelProductManager(Model model) {
        try {
            var obj = model.getAttribute("rowFilter");
            log.error("jewelProductManager(): " + obj);
        } catch (Exception _e) {
            log.error("error render", _e);
        }
        return "pages/jewel-manager"; // view
    }

}
