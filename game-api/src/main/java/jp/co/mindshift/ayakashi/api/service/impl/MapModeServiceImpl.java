package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.Constant;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.config.ActionUserHolder;
import jp.co.mindshift.ayakashi.api.model.PositionLog;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.repo.PositionLogRepository;
import jp.co.mindshift.ayakashi.api.repo.UsersRepository;
import jp.co.mindshift.ayakashi.api.service.MapModeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static jp.co.mindshift.ayakashi.api.common.Constant.MAX_UPDATE_POSITION_GAP_IN_SECS;
import static jp.co.mindshift.ayakashi.api.common.Constant.PEOPLE_COUNTER_STEP;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 05/05/2022<br/>
 */
@AllArgsConstructor
@Service
public class MapModeServiceImpl implements MapModeService {
    private final PositionLogRepository positionLogRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public ResponseDTO<?> updatePositionMatrix(Long currentPosId) {
        ActionUserDTO user = ActionUserHolder.getActionUser();
        Optional<Users> userId = usersRepository.findByUsernameAndStatusIn(user.getSub(),
                Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        Assert.isTrue(userId.isPresent(), MsgUtil.getMessage("user.info.null"));
        Long uid = userId.get().getId();
        var now = new Date();
        var pos = PositionLog.builder()
                .userId(uid)
                .posId(currentPosId)
                .createdAt(now)
                .updatedAt(now)
                .build();
        positionLogRepository.save(pos);

        // get list position
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.SECOND, MAX_UPDATE_POSITION_GAP_IN_SECS);
        List<PositionLog> matrix = positionLogRepository.findAllByUpdatedAtAfter(cal.getTime());
        Map<Long, Integer> population = new HashMap<>();
        for (var positionLog: matrix) {
            Integer count = population.get(positionLog.getPosId());
            if (count == null) {
                count = 0;
            }
            count += 1;
            population.put(positionLog.getPosId(), count);
        }
        var sets = population.entrySet();
        Map<Long, Integer> result = new HashMap<>();
        for (var s: sets) {
            result.put(s.getKey(), s.getValue()/PEOPLE_COUNTER_STEP);
        }
        return ResponseDTO.success(result);
    }
}
