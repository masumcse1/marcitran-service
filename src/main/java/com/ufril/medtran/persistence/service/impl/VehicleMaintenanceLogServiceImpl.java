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
    private VehicleMaintenanceLogRepository vehicleMaintRepo;

    @Override
    public List<VehicleMaintenanceLog> getAllVehicleMaintenanceLog(Date startDate, Date endDate, Integer vehicleId, Pageable pageable) {
        List<VehicleMaintenanceLog> data = new ArrayList<>();

        if(vehicleId != null)
            data = vehicleMaintRepo.findByDateBetweenAndVehicles_Id(startDate, endDate, vehicleId, pageable);
        else
            data = vehicleMaintRepo.findByDateBetween(startDate, endDate, pageable);
        return data;
    }

    @Override
    public VehicleMaintenanceLog getVehicleMaintenanceLogById(int id) {
        return vehicleMaintRepo.findOne(id);
    }

    @Override
    @Transactional
    public VehicleMaintenanceLog createVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog) {
        return vehicleMaintRepo.save(vehicleMaintenanceLog);
    }

    @Override
    @Transactional
    public VehicleMaintenanceLog updateVehicleMaintenanceLog(VehicleMaintenanceLog vehicleMaintenanceLog) {
        return vehicleMaintRepo.save(vehicleMaintenanceLog);
    }

    @Override
    public Boolean deleteVehicleMaintenanceLog(int id) {
        vehicleMaintRepo.delete(id);
        return true;
    }
}
