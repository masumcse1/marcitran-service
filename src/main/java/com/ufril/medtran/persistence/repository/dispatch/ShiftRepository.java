package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shifts, Integer> {

    List<Shifts> findAllByCompanyId(Integer companyId);

    List<Shifts> findAllByCompanyIdAndStatus(Integer companyId,
                                             Integer status,
                                             Pageable pageable);

}
