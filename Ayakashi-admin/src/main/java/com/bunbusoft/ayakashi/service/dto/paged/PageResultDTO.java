package com.bunbusoft.ayakashi.service.dto.paged;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 27/03/2022<br/>
 * Time: 21:38<br/>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResultDTO <T> {
    private Pageable pageable;
    private long totalPages;
    private long totalElements;
    private List<T> content;
}
