package io.github.eureka.zuulserver.config;

import io.github.eureka.zuulserver.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;

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
        map.put("status", ResponseCode.SUCCESS);
        String[] split = map.get("message").toString().split(SPLIT_CHAR);
        if (split.length == 2) {
            map.put("error", split[0]);
            map.put("message", split[1]);
        }
        return map;
    }

}
