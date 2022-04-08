package io.github.eureka.api.service.impl;

import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;
import io.github.eureka.api.repo.ScenariosRepository;
import io.github.eureka.api.service.ScenariosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenariosServiceImpl implements ScenariosService {
    private final ScenariosRepository scenariosRepository;

    public ScenariosServiceImpl(ScenariosRepository scenariosRepository) {
        this.scenariosRepository = scenariosRepository;
    }

    @Override
    public BaseMsgDTO<List<ScenariosDTO>> getListScenarios() {
        return null;
    }
}
