package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.DataUtil;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.model.Scenarios;
import jp.co.mindshift.ayakashi.api.model.dto.ScenariosDTO;
import jp.co.mindshift.ayakashi.api.repo.ScenariosRepository;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.ScenariosService;
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
