package com.bunbusoft.ayakashi.service.dto.paged;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BasePageDTO {
    private int currentPage; // current page
    private int pageSize; // size per page
    private String sortField; // sort field
    private boolean isASC; // direction
}
