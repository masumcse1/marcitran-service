package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.TimeClock;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TimeClockService {

    List<TimeClock> getAllTimeClock(Integer companyId, Pageable pageable);

    TimeClock getTimeClockById(int id);

    List<TimeClock> getTimeClockByEmployeeAndDate(Employees employee, Date date);

    List<TimeClock> getTimeClockByDate(Integer companyId, Date date);

    TimeClock createTimeClock(TimeClock timeClock);

    TimeClock updateTimeClock(TimeClock timeClock);

    boolean deleteTimeClock(int id);
}
