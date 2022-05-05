package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 05/05/2022<br/>
 */
public interface MapModeService {
    ResponseDTO<?> updatePositionMatrix(Long currentPosId);
}
