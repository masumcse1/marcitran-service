package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.VehicleMaintenanceLog;
import com.ufril.medtran.persistence.repository.dispatch.VehicleMaintenanceLogRepository;
import com.ufril.medtran.persistence.service.VehicleMaintenanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VehicleMaintenanceLogServiceImpl implements VehicleMaintenanceLogService {

    @Autowired
    private VehicleMaintenanceLogRepository vehicleMainRepo;

    @Override
    public List<VehicleMaintenanceLog> getAllVehicleMaintenanceLog(Integer companyId,
                                                                   Date startDate,
                                                                   Date endDate,
                                                                   Integer vehicleId,
                                                                   Pageable pageable) {

        if (vehicleId != null)
            return vehicleMainRepo.findByCompanyIdAndDateBetweenAndVehicles_Id(
                    companyId, startDate, endDate, vehicleId, pageable);
        else
            return vehicleMainRepo.findByCompanyIdAndDateBetween(
                    companyId, startDate, endDate, pageable);
    }

    @Override
    public VehicleMaintenanceLog getVehicleMaintenanceLogById(int id) {
        return vehicleMainRepo.findOne(id);
    }

    @Override
    @Transactional
    public VehicleMaintenanceLog createVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog) {
        return vehicleMainRepo.save(vehicleMaintenanceLog);
    }

    @Override
    @Transactional
    public VehicleMaintenanceLog updateVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog) {
        return vehicleMainRepo.save(vehicleMaintenanceLog);
    }

    @Override
    public Boolean deleteVehicleMaintenanceLog(int id) {
        vehicleMainRepo.delete(id);
        return true;
    }
}
