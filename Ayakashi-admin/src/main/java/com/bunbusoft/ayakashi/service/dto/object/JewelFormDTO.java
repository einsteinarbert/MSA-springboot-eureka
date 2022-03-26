package com.bunbusoft.ayakashi.service.dto.object;

import com.bunbusoft.ayakashi.service.dto.FilterJewelDTO;
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
public class JewelFormDTO extends BasePageDTO {
    private List<FilterJewelDTO> filter;
}
