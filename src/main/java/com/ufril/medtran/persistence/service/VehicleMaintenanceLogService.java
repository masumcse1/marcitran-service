package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.VehicleMaintenanceLog;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VehicleMaintenanceLogService {
    List<VehicleMaintenanceLog> getAllVehicleMaintenanceLog(Date startDate, Date endDate, Integer vehicleId, Pageable pageable);

    VehicleMaintenanceLog getVehicleMaintenanceLogById(int id);

    VehicleMaintenanceLog createVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog);

    VehicleMaintenanceLog updateVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog);

    Boolean deleteVehicleMaintenanceLog(int id);

}
