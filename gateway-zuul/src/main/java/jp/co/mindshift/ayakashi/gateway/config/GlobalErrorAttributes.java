package jp.co.mindshift.ayakashi.gateway.config;

import jp.co.mindshift.ayakashi.gateway.common.Constant;
import jp.co.mindshift.ayakashi.gateway.common.ResponseCode;
import jp.co.mindshift.ayakashi.gateway.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static jp.co.mindshift.ayakashi.gateway.common.MsgUtil.SPLIT_CHAR;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 14:10<br/>
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> map;
        try {
            map = super.getErrorAttributes(request, options);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("timestamp", new Date());
            if (e.getMessage().contains("No matching constant for [9]")) {
                map.put("status", HttpStatus.UNAUTHORIZED.value());
                map.put("statusCode", Constant.TOKEN_EXPIRED_STATUS);
            } else {
                map.put("status", "500");
                map.put("statusCode", "1");
            }
            return map;
        }
        Object obj = map.get("message");
        String msg = obj == null ? "" : obj.toString();
        String[] split = msg.split(SPLIT_CHAR);
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        if (split.length == 2) {
            responseDTO.setMessage(split[1]);
            responseDTO.setData(split[0]);
        } else {
            responseDTO.setMessage(msg);
        }
        int code = (int) map.get("status");
        convertStatus(msg, code, responseDTO);
        parameters(responseDTO, map);
        return map;
    }

    private void convertStatus(String msg, int status, ResponseDTO<String> responseDTO) {
        if (status == 401 && msg.contains("JWT expired at")) {
            responseDTO.setStatusCode(ResponseCode.TOKEN_EXPIRED);
        } else if (status > 399 || status < 200) {
            responseDTO.setStatusCode(ResponseCode.FAILURE);
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
