package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.PCRLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRLogRepository extends JpaRepository<PCRLog, Integer> {

    PCRLog findByDispatchId(int dispatchId);
}
