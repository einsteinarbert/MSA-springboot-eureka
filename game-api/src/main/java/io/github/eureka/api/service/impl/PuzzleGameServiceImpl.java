package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ResponseDTO;
import io.github.eureka.api.model.entity.UserDTO;
import io.github.eureka.api.model.form.PuzzleGameForm;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.PuzzleGameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PuzzleGameServiceImpl extends BaseService implements PuzzleGameService {
	private final UsersRepository usersRepository;
	
	
	@Override
	public ResponseDTO<?> endGameProcess(PuzzleGameForm puzzleGameForm) {
		Optional<Users> optionalUsers = usersRepository.findById(puzzleGameForm.getUserId());
		Assert.isTrue(optionalUsers.isPresent(), MsgUtil.getMessage("user.info.null"));
		Users users = optionalUsers.get();
		users.setStage(users.getStage() + 1);
		users = usersRepository.save(users);
		return ResponseDTO.success(super.map(users, UserDTO.class));
	}
}
