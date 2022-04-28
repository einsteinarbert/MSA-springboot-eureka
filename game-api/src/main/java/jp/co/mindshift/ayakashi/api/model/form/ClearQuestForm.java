package jp.co.mindshift.ayakashi.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClearQuestForm {
	private Long userId;
	private Long questId;
}
