package jp.co.mindshift.ayakashi.api.controller;

import io.eventuate.common.json.mapper.JSonMapper;
import jp.co.mindshift.ayakashi.api.config.ActionUserHolder;
import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:02<br/>
 */
public abstract class BaseController {
    /**
     * get user info and save to context
     * @param request contain token
     */
    void setUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        ActionUserDTO user = JSonMapper.fromJson(payload, ActionUserDTO.class);
        ActionUserHolder.setActionUser(user);
    }
}
