package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.VehicleMaintenanceLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleMaintenanceLogRepository extends JpaRepository<VehicleMaintenanceLog, Integer> {

    List<VehicleMaintenanceLog> findByCompanyIdAndDateBetween(Integer companyId,
                                                              Date startDate,
                                                              Date endDate,
                                                              Pageable pageable);

    List<VehicleMaintenanceLog> findByCompanyIdAndDateBetweenAndVehicles_Id(Integer companyId,
                                                                            Date startDate,
                                                                            Date endDate,
                                                                            Integer vehicleId,
                                                                            Pageable pageable);
}
