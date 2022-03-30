package com.bunbusoft.ayakashi.service.dto.object;

import com.bunbusoft.ayakashi.service.dto.paged.BasePageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchFormDTO extends BasePageDTO {
    private List<FilterDTO> filter;
}
