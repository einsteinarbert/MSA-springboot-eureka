package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.DataUtil;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Scenarios;
import io.github.eureka.api.model.dto.BaseMsgDTO;
import io.github.eureka.api.model.dto.ScenariosDTO;
import io.github.eureka.api.repo.ScenariosRepository;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.ScenariosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenariosServiceImpl extends BaseService implements ScenariosService {
    private final ScenariosRepository scenariosRepository;

    public ScenariosServiceImpl(ScenariosRepository scenariosRepository) {
        this.scenariosRepository = scenariosRepository;
    }

    @Override
    public List<ScenariosDTO> getListScenarios() {
        return super.mapList(scenariosRepository.findAll(), ScenariosDTO.class);
    }

    @Override
    public ScenariosDTO findScenarioById(Long id) {
        Scenarios existScenario = scenariosRepository.getById(id);
        if (DataUtil.isNullOrEmpty(existScenario)){
            throw new IllegalArgumentException(MsgUtil.getMessage("scenario.notfound"));
        }
        return super.map(existScenario, ScenariosDTO.class);
    }
}
