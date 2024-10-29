package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JourneyLogsRepository extends JpaRepository<JourneyLogs, Integer> {

    List<JourneyLogs> findByCompanyIdAndTransportBeginTimeBetweenAndVehicle_Id(Integer companyId,
                                                                               Date startDate,
                                                                               Date endDate,
                                                                               Integer vehicleId,
                                                                               Pageable pageable);

    List<JourneyLogs> findByCompanyIdAndTransportBeginTimeBetween(Integer companyId,
                                                                  Date startDate,
                                                                  Date endDate,
                                                                  Pageable pageable);
}
