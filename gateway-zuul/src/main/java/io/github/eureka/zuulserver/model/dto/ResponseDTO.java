package io.github.eureka.zuulserver.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.eureka.zuulserver.common.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
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
public class ResponseDTO<T> implements Serializable {
    @JsonInclude()
    @Transient
    public static final String OK = "success";
    @JsonInclude()
    @Transient
    public static final String NG = "failure";
    @Builder.Default
    private int statusCode = ResponseCode.SUCCESS;
    @Builder.Default
    private String message = OK;
    private T data;

    public ResponseDTO(T data) {
        this.data = data;
    }
    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(data);
    }

    public static <T> ResponseDTO<T> success(T data, int i) {
        return ResponseDTO.<T>builder().data(data).statusCode(i).build();
    }
}
