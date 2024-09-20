package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.dto.dispatch.JourneyLogDTO;
import com.ufril.medtran.persistence.domain.dispatch.Dispatches;
import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.JourneyLogs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JourneyLogsRepository extends JpaRepository<JourneyLogs, Integer> {
    List<JourneyLogs> findByTransportBeginTimeBetweenAndVehicle_Id(Date startDate, Date endDate, Integer vehicleId, Pageable pageable);
    List<JourneyLogs> findByTransportBeginTimeBetween(Date startDate, Date endDate, Pageable pageable);
}
