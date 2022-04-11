package io.github.eureka.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 17:15<br/>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseMsgDTO<T> implements Serializable {
    @JsonIgnore
    public static final String OK = "success";
    @JsonIgnore
    public static final String NG = "failure";
    private int code;
    private String reason;
    private String message;
    private T data;

    public BaseMsgDTO(T data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public static <T> BaseMsgDTO<T> success(T data) {
        return new BaseMsgDTO<>(data);
    }
}
