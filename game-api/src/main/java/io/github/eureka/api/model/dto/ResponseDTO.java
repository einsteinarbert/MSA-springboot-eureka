package io.github.eureka.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.eureka.api.model.UserItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;

import static io.github.eureka.api.common.ResponseCode.SUCCESS;

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
    private int code = SUCCESS;
    @Builder.Default
    private String status = OK;
    private String message;
    private T data;

    public ResponseDTO(T data) {
        this.data = data;
    }
    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(data);
    }

    public static <T> ResponseDTO<T> success(T data, int i) {
        return ResponseDTO.<T>builder().data(data).code(i).build();
    }
}
