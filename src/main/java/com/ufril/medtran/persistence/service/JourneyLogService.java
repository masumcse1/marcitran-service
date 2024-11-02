package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface JourneyLogService {

    List<JourneyLogs> getAllJourneyLogs(Integer companyId,
                                        Date startDate,
                                        Date endDate,
                                        Integer vehicleId,
                                        Pageable pageable);

    JourneyLogs getJourneyLogById(int id);

    JourneyLogs createJourneyLog(JourneyLogs journeyLog);

    void updateJourneyLog(JourneyLogs journeyLog);

    Boolean deleteJourneyLog(int id);
}
