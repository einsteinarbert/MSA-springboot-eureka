package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ScenariosDTO;

import java.util.List;

public interface ScenariosService {
    List<ScenariosDTO> getListScenarios();

    ScenariosDTO findScenarioById(Long id);
}
