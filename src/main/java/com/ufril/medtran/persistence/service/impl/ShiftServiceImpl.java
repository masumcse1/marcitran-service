package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import com.ufril.medtran.persistence.domain.dispatch.ShiftCrewMembers;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.repository.dispatch.JourneyLogsRepository;
import com.ufril.medtran.persistence.repository.dispatch.ShiftCrewMemberRepository;
import com.ufril.medtran.persistence.repository.dispatch.ShiftRepository;
import com.ufril.medtran.persistence.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftCrewMemberRepository shiftCrewMemberRepository;

    @Autowired
    private JourneyLogsRepository journeyLogsRepository;

    @Override
    public List<Shifts> getAllShifts(Integer companyId, Integer status, Pageable pageable) {
        return shiftRepository.findAllByCompanyIdAndStatus(companyId, status, pageable);
    }

    @Override
    public List<Shifts> getAllShiftsByDate(Integer companyId, Date fromDate, Date toDate) {
        return null;
    }

    @Override
    public List<Shifts> getAllShiftsByDispatch(int dispatchId) {
        return null;
    }

    @Override
    public Shifts getShiftById(int id) {
        return shiftRepository.findOne(id);
    }

    @Override
    public Shifts createShift(Shifts shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Shifts updateShift(Shifts shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public boolean deleteShift(int id) {
        Shifts shifts = shiftRepository.findOne(id);
        shifts.setStatus(0);
        shiftRepository.save(shifts);
        return true;
    }

    @Override
    public void mapShiftCrewMembers(List<ShiftCrewMembers> shiftCrewMembers) {
        shiftCrewMemberRepository.save(shiftCrewMembers);
    }

    @Override
    public void addJourneyLogs(JourneyLogs journeyLogs) {
        journeyLogsRepository.save(journeyLogs);
    }
}
