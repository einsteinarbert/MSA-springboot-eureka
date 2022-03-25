package com.bunbusoft.ayakashi.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 24/03/2022<br/>
 * Time: 14:50<br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RowFilter implements Serializable {
    private List<Selector> selectors;
    private boolean isDefault;
}
