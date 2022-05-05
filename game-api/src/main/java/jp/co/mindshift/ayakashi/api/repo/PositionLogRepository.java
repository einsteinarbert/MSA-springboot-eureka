package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.PositionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PositionLogRepository extends JpaRepository<PositionLog, Long> {
    List<PositionLog> findAllByUpdatedAtAfter(Date oldest);
}
