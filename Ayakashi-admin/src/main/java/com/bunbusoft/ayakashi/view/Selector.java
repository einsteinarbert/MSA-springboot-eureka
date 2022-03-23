package com.bunbusoft.ayakashi.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 23/03/2022<br/>
 * Time: 15:38<br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Selector {
    private boolean showLabel = true;
    private String labelValue;
    private boolean showToolTip = true;
    private String toolTip;
    private List<Option> options;
}
