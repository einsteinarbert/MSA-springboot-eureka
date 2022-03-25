package com.bunbusoft.ayakashi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterJewelDTO {
    private String field;
    private String condition;
    private String keyword;
}
