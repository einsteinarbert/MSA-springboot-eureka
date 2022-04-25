package io.github.eureka.zuulserver.config;

import io.github.eureka.zuulserver.common.ResponseCode;
import io.github.eureka.zuulserver.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.Map;

import static io.github.eureka.zuulserver.common.MsgUtil.SPLIT_CHAR;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 14:10<br/>
 */
@Slf4j
@ControllerAdvice
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(
                request, options);
        String msg = map.get("message").toString();
        String[] split = msg.split(SPLIT_CHAR);
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        if (split.length == 2) {
            responseDTO.setCode(split[0]);
            responseDTO.setMessage(split[1]);
        } else {
            responseDTO.setMessage(msg);
            responseDTO.setCode(ResponseDTO.NG);
        }
        int code = (int) map.get("status");
        convertStatus(msg, code, responseDTO);
        map.put("status", 200);
        parameters(responseDTO, map);
        return map;
    }

    private void convertStatus(String msg, int status, ResponseDTO<String> responseDTO) {
        if (status == 500 && msg.contains("JWT expired at")) {
            responseDTO.setStatus(ResponseCode.TOKEN_EXPIRED);
            responseDTO.setCode("JWT expired");
        }
    }

    public static void parameters(Object obj, Map<String, Object> map) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(Transient.class) != null)
                continue;
            try { map.put(field.getName(), field.get(obj)); } catch (Exception ignored) { }
        }
    }

}
