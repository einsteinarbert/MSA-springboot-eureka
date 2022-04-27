package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.model.entity.UserDTO;
import io.github.eureka.api.model.entity.UserDataEntity;
import io.github.eureka.api.model.form.PuzzleGameForm;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.PuzzleGameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PuzzleGameServiceImpl extends BaseService implements PuzzleGameService {
	private final UsersRepository usersRepository;
	@PersistenceContext
	EntityManager em;
	public static final String sqlListItem = "select ui.user_id, ui.item_id, sum(ui.number) number, max(si.picture) picture\n" +
			"from user_items ui left join special_items si on ui.item_id = si.item_id where ui.user_id = :userId and si.special_item_type = 4\n" +
			"group by ui.user_id, ui.item_id";
	
	@Override
	public ResponseDTO<?> endGameProcess(PuzzleGameForm puzzleGameForm) {
		Optional<Users> optionalUsers = usersRepository.findById(puzzleGameForm.getUserId());
		Assert.isTrue(optionalUsers.isPresent(), MsgUtil.getMessage("user.info.null"));
		Users users = optionalUsers.get();
		users.setStage(users.getStage() + 1);
		users = usersRepository.save(users);
		return ResponseDTO.success(super.map(users, UserDTO.class));
	}

	@Override
	public ResponseDTO<?> getListItemPuzzleGame(Long userId) {
		Optional<Users> optionalUsers = usersRepository.findById(userId);
		Assert.isTrue(optionalUsers.isPresent(), MsgUtil.getMessage("user.info.null"));
		PuzzleItemDTO puzzleItem =(PuzzleItemDTO) em.createNativeQuery(sqlListItem, PuzzleItemDTO.class)
				.setParameter("userId", userId)
				.getResultList();
		PuzzleItemDTO puzzleItemDTO = super.map(puzzleItem, PuzzleItemDTO.class);
		return ResponseDTO.success(puzzleItemDTO);
	}
}
