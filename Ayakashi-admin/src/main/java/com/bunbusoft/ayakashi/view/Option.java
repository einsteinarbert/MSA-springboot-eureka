package com.bunbusoft.ayakashi.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 23/03/2022<br/>
 * Time: 15:39<br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Option implements Serializable {
    private String text;
    private int value;
}
