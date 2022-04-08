package io.github.eureka.api.controller;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;
import io.github.eureka.api.service.ScenariosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ScenariosController {
    private final ScenariosService scenariosService;

    @GetMapping("/users/scenarios")
    public BaseMsgDTO<List<ScenariosDTO>> getListScenario(){
        return scenariosService.getListScenarios();
    }


}
