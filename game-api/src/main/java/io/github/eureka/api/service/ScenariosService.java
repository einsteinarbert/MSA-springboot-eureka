package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;

import java.util.List;

public interface ScenariosService {
    List<ScenariosDTO> getListScenarios();

    BaseMsgDTO<List<ScenariosDTO>> findScenarioById(Long id);
}
