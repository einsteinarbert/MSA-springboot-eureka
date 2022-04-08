package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;

import java.util.List;

public interface ScenariosService {
    BaseMsgDTO<List<ScenariosDTO>> getListScenarios();
}
