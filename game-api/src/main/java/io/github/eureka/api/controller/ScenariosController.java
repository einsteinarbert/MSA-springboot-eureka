package io.github.eureka.api.controller;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;
import io.github.eureka.api.service.ScenariosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ScenariosController {
    private final ScenariosService scenariosService;

    @GetMapping("/scenarios")
    public BaseMsgDTO<List<ScenariosDTO>> getListScenario(){
        return BaseMsgDTO.success(scenariosService.getListScenarios());
    }
    
    @GetMapping("/scenario-detail/{id}")
    public BaseMsgDTO<ScenariosDTO> getListScenario(@PathVariable Long id){
        return BaseMsgDTO.success(scenariosService.findScenarioById(id));
    }


}
