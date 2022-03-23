package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.view.Option;
import com.bunbusoft.ayakashi.view.Selector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "pages")
@Slf4j
public class WalletController {

    /**
     * get view
     * @return jewel-manager.html
     */
    @GetMapping(value = "jewel-manager")
    public String jewelProductManager(Model model) {
        log.info("jewelProductManager()");
        List<Option> lst = Arrays.asList(
                new Option("field1", "1"),
                new Option("field2 longly loooog", "2"),
                new Option("field3", "3")
        );
        model.addAttribute("operations", Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("フィールド")
                .options(lst)
                .build()
        );

        List<Option> operators = Arrays.asList(
                new Option("=", "1"),
                new Option("LIKE", "2"),
                new Option("NOT LIKE", "3")
        );
        model.addAttribute("conditions", Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("条件")
                .options(operators)
                .build()
        );
        model.addAttribute("keywords", Selector.builder()
                .showLabel(false)
                .showToolTip(true)
                .toolTip("キーワード")
                .options(lst)
                .build()
        );
        return "pages/jewel-manager"; // view
    }

}
