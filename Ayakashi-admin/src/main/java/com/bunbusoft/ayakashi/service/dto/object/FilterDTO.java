package com.bunbusoft.ayakashi.service.dto.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterDTO {
    private String field;
    private String condition;
    private String keyword;
}
