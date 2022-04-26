package io.github.eureka.api.controller;

import io.github.eureka.api.model.dto.ResponseDTO;
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
@RequestMapping("/api/v1")
public class ScenariosController {
    private final ScenariosService scenariosService;

    @GetMapping("/scenarios")
    public ResponseDTO<List<ScenariosDTO>> getListScenario(){
        return ResponseDTO.success(scenariosService.getListScenarios());
    }
    
    @GetMapping("/scenario-detail/{id}")
    public ResponseDTO<ScenariosDTO> getListScenario(@PathVariable Long id){
        return ResponseDTO.success(scenariosService.findScenarioById(id));
    }


}
