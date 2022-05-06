package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.Constant;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.config.ActionUserHolder;
import jp.co.mindshift.ayakashi.api.model.Quests;
import jp.co.mindshift.ayakashi.api.model.UserQuests;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.form.ClearQuestForm;
import jp.co.mindshift.ayakashi.api.repo.QuestsRepository;
import jp.co.mindshift.ayakashi.api.repo.UserQuestsRepository;
import jp.co.mindshift.ayakashi.api.repo.UsersRepository;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.QuestsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static jp.co.mindshift.ayakashi.api.common.Constant.MAX_DAY_REFRESH_QUEST;
import static jp.co.mindshift.ayakashi.api.common.Constant.MAX_OLD_DAY_QUEST;
import static jp.co.mindshift.ayakashi.api.common.Constant.MAX_QUEST;

@Service
@AllArgsConstructor
public class QuestsServiceImpl extends BaseService implements QuestsService {
	private final QuestsRepository questsRepository;
	private final UserQuestsRepository userQuestsRepository;
	private final UsersRepository usersRepository;
	@Override
	@Transactional
	public ResponseDTO<?> getList() {
		var user = ActionUserHolder.getActionUser();
		Optional<Users> userId = usersRepository.findByUsernameAndStatusIn(user.getSub(),
				Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		Assert.isTrue(userId.isPresent(), MsgUtil.getMessage("user.info.null"));
		Long uid = userId.get().getId();
		// find all available quest for this user (min 0, max 5)
		List<UserQuests> activeQuests = userQuestsRepository.findAllByUserIdAndStatusIn(uid, Collections.singletonList
				(Constant.UserQuestStatus.AVAILABLE.getType()));
		// find all playing quest (min 0, max 1)
		List<UserQuests> playingQuests = userQuestsRepository.findAllByUserIdAndStatusIn(uid, Collections.singletonList
				(Constant.UserQuestStatus.PLAYING.getType()));
		// if total (playing + available) quest > 5 or activeQuests not in [0, 5] or playingQuests > 1 then make cancel
		//  some quests for re-balance quests quality
		List<Long> cancelQuest = new ArrayList<>();
		Date now = new Date();
		if (activeQuests.size() > MAX_QUEST) {
			int count = activeQuests.size() - MAX_QUEST;
			if (playingQuests.size() > 0) {
				count+=1;
			}
			balanceQuests(activeQuests, cancelQuest, now, count);
		}
		if (activeQuests.size() - cancelQuest.size() + playingQuests.size() > MAX_QUEST) {
			int count = activeQuests.size() - cancelQuest.size() + playingQuests.size() - MAX_QUEST;
			balanceQuests(playingQuests, cancelQuest, now, count);
		}

		// find all canceled, cleared quests in last week:
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, MAX_OLD_DAY_QUEST);
		Date last7Days = c.getTime();
		c.setTime(now);
		c.add(Calendar.DATE, MAX_DAY_REFRESH_QUEST);
		Date last2Days = c.getTime();
		List<UserQuests> inactiveQuests = userQuestsRepository.findAllByUserIdAndCreatedAtBetweenAndStatusIn(uid,
				last7Days, now, Arrays.asList(Constant.UserQuestStatus.CANCEL.getType(),
						Constant.UserQuestStatus.CLEARED.getType()));
		List<UserQuests> refreshQuests = userQuestsRepository.findAllByUserIdAndCreatedAtBetweenAndStatusIn(uid,
				last2Days, now, List.of(Constant.UserQuestStatus.AVAILABLE.getType()));
		for (var quest: refreshQuests) {
			quest.setStatus(Constant.UserQuestStatus.CANCEL.getType());
			userQuestsRepository.save(quest);
			cancelQuest.add(quest.getQuestId());
		}
		List<Long> inactiveIds = inactiveQuests.stream().map(UserQuests::getQuestId).collect(Collectors.toList());
		cancelQuest.addAll(inactiveIds);


		// now get list quest again
		activeQuests = userQuestsRepository.findAllByUserIdAndStatusIn(uid, Collections.singletonList
				(Constant.UserQuestStatus.AVAILABLE.getType()));
		playingQuests = userQuestsRepository.findAllByUserIdAndStatusIn(uid, Collections.singletonList
				(Constant.UserQuestStatus.PLAYING.getType()));
		List<Long> activeIds = activeQuests.stream().map(UserQuests::getQuestId).collect(Collectors.toList());
		activeIds.addAll(playingQuests.stream().map(UserQuests::getQuestId).collect(Collectors.toList()));
		cancelQuest.addAll(activeIds);
		List<Quests> questsAssigned = questsRepository.findAllByIdIn(activeIds);
		List<Quests> newFreshQuests = questsRepository.findByIdNotIn(cancelQuest);
		int counter = activeIds.size();
		int min = 0;
		int max = newFreshQuests.size() - 1;
		while (counter < MAX_QUEST) {
			Random r = new Random();
			int ran = r.nextInt(max - min + 1) + min;
			var q = newFreshQuests.get(ran);
			UserQuests userQuests = new UserQuests();
			userQuests.setQuestId(q.getId());
			userQuests.setUserId(uid);
			userQuests.setCreatedAt(now);
			userQuests.setStatus(Constant.UserQuestStatus.AVAILABLE.getType());
			userQuestsRepository.save(userQuests);
			questsAssigned.add(q);
			counter++;
		}
		return ResponseDTO.success(questsAssigned);
	}

	private void balanceQuests(List<UserQuests> userQuests, List<Long> cancelQuest, Date now, int count) {
		for (UserQuests quest : userQuests) {
			if (count > 0) {
				count--;
				quest.setStatus(Constant.UserQuestStatus.CANCEL.getType());
				quest.setUpdatedAt(now);
				userQuestsRepository.save(quest);
				cancelQuest.add(quest.getQuestId());
			} else {
				break;
			}
		}
	}

	@Override
	public ResponseDTO<?> clearQuest(ClearQuestForm clearQuestForm) {
		UserQuests newUserQuest = super.map(clearQuestForm, UserQuests.class);
		newUserQuest.setUpdatedAt(new Date());
		newUserQuest.setCreatedAt(new Date());
		userQuestsRepository.save(newUserQuest);
		return ResponseDTO.builder().build();
	}
}
