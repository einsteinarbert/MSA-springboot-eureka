package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.securities.FieldMatch;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "pages")
@Slf4j
@Scope("request")
public class WalletController {
    @Autowired
    private JewelState state;

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
