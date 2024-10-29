package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.TimeClock;
import com.ufril.medtran.persistence.repository.account.TimeClockRepository;
import com.ufril.medtran.persistence.service.TimeClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TimeClockServiceImpl implements TimeClockService {

    @Autowired
    private TimeClockRepository timeClockRepository;

    @Override
    public List<TimeClock> getAllTimeClock(Integer companyId, Pageable pageable) {
        return timeClockRepository.findAllByCompanyId(companyId, pageable)
                .getContent();
    }

    @Override
    public TimeClock getTimeClockById(int id) {
        return timeClockRepository.findOne(id);
    }

    @Override
    public List<TimeClock> getTimeClockByEmployeeAndDate(Employees employee, Date date) {
        return timeClockRepository.findAllByEmployeeAndClockInAfter(employee, date);
    }

    @Override
    public List<TimeClock> getTimeClockByDate(Integer companyId, Date date) {
        return timeClockRepository.findAllByCompanyIdAndClockInAfter(companyId, date);
    }

    @Override
    @Transactional
    public TimeClock createTimeClock(TimeClock timeClock) {
        return timeClockRepository.save(timeClock);
    }

    @Override
    @Transactional
    public TimeClock updateTimeClock(TimeClock timeClock) {
        return timeClockRepository.save(timeClock);
    }

    @Override
    @Transactional
    public boolean deleteTimeClock(int id) {
        timeClockRepository.delete(id);
        return true;
    }
}
