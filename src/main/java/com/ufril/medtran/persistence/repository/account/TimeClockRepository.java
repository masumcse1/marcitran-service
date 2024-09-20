package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.dto.account.TimeClockDTO;
import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.TimeClock;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface TimeClockRepository extends JpaRepository<TimeClock, Integer> {
    List<TimeClock> findAllByEmployeeAndClockInAfter(Employees employee, @Temporal(TemporalType.DATE) Date clockIn);

    List<TimeClock> findAllByClockInAfter(@Temporal(TemporalType.DATE) Date clockIn);

}
