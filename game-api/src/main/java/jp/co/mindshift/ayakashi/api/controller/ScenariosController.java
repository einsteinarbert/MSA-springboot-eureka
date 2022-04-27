package jp.co.mindshift.ayakashi.api.controller;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.dto.ScenariosDTO;
import jp.co.mindshift.ayakashi.api.service.ScenariosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("scenarios")
public class ScenariosController {
    private final ScenariosService scenariosService;

    @GetMapping("get")
    public ResponseDTO<List<ScenariosDTO>> getListScenario(){
        return ResponseDTO.success(scenariosService.getListScenarios());
    }
    
    @GetMapping("/detail/{id}")
    public ResponseDTO<ScenariosDTO> getListScenario(@PathVariable Long id){
        return ResponseDTO.success(scenariosService.findScenarioById(id));
    }


}
