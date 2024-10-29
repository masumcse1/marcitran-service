package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.VehicleMaintenanceLog;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface VehicleMaintenanceLogService {

    List<VehicleMaintenanceLog> getAllVehicleMaintenanceLog(Integer companyId,
                                                            Date startDate,
                                                            Date endDate,
                                                            Integer vehicleId,
                                                            Pageable pageable);

    VehicleMaintenanceLog getVehicleMaintenanceLogById(int id);

    VehicleMaintenanceLog createVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog);

    VehicleMaintenanceLog updateVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog);

    Boolean deleteVehicleMaintenanceLog(int id);

}
