package com.bunbusoft.ayakashi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "pages")
@Slf4j
public class WalletController {

    @GetMapping(value = "jewel-manager")
    public ModelMap jewelProductManager() {
        log.info("jewelProductManager()");
        return new ModelMap();
    }

}
