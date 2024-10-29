package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.TimeClock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface TimeClockRepository extends JpaRepository<TimeClock, Integer> {

    Page<TimeClock> findAllByCompanyId(Integer companyId, Pageable pageable);

    List<TimeClock> findAllByEmployeeAndClockInAfter(Employees employee,
                                                     @Temporal(TemporalType.DATE) Date clockIn);

    List<TimeClock> findAllByCompanyIdAndClockInAfter(Integer companyId,
                                                      @Temporal(TemporalType.DATE) Date clockIn);

}
