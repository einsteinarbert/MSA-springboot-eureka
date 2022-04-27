package jp.co.mindshift.ayakashi.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PuzzleItemDTO {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("item_id")
    private Long itemId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("picture")
    private String picture;
}
