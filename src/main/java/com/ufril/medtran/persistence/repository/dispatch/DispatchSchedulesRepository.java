package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.DispatchSchedules;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchSchedulesRepository extends JpaRepository<DispatchSchedules, Integer> {
    DispatchSchedules findByDispatch(Dispatches dispatch);
}
