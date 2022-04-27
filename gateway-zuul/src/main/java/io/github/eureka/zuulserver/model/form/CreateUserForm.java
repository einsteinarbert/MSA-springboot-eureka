package io.github.eureka.zuulserver.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 27/04/2022<br/>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {
    String deviceId;
    String name;
    Date birthday;
    String username;
    Integer gender;
}
