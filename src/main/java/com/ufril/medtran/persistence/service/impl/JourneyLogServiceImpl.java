package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.dispatch.JourneyLogDTO;
import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import com.ufril.medtran.persistence.repository.dispatch.JourneyLogsRepository;
import com.ufril.medtran.persistence.service.JourneyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JourneyLogServiceImpl implements JourneyLogService {

    @Autowired
    private JourneyLogsRepository journeyLogsRepository;

    @Override
    public List<JourneyLogs> getAllJourneyLogs(Date startDate, Date endDate, Integer vehicleId, Pageable pageable) {
        if (vehicleId != null)
            return journeyLogsRepository.findByTransportBeginTimeBetweenAndVehicle_Id(startDate, endDate, vehicleId, pageable);
        else
            return journeyLogsRepository.findByTransportBeginTimeBetween(startDate, endDate, pageable);
    }

    @Override
    public JourneyLogs getJourneyLogById(int id) {
        return journeyLogsRepository.findOne(id);
    }

    @Override
    public JourneyLogs createJourneyLog(JourneyLogs journeyLog) {
        return journeyLogsRepository.save(journeyLog);
    }

    @Override
    public JourneyLogs updateJourneyLog(JourneyLogs journeyLog) {
        return journeyLogsRepository.save(journeyLog);
    }

    @Override
    public Boolean deleteJourneyLog(int id) {
        journeyLogsRepository.delete(id);
        return true;
    }
}
