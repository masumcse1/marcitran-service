package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.DispatchLogs;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchLogsRepository extends JpaRepository<DispatchLogs, Integer> {
    DispatchLogs findByDispatch(Dispatches dispatch);
}
