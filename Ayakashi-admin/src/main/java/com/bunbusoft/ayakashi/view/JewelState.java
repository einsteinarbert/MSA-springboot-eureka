package com.bunbusoft.ayakashi.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 24/03/2022<br/>
 * Time: 16:27<br/>
 */
@Component
@Scope("session")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JewelState {
    List<RowFilter> data;
}
