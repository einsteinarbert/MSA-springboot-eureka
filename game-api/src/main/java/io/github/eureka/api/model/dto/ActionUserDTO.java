package io.github.eureka.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 11:28<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionUserDTO implements Serializable {
    private List<String> role;
    private String sub; // subscriber = username = device id
    private Date iat; // issued at time
    private Date exp; // expired date
}
