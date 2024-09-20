package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.repository.dispatch.FuelPurchaseLogRepository;
import com.ufril.medtran.persistence.service.FuelPurchaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FuelPurchaseLogServiceImpl implements FuelPurchaseLogService {

    @Autowired
    private FuelPurchaseLogRepository fuelPurchaseLogRepository;

    @Override
    public List<FuelPurchaseLog> getAllFuelPurchaseLogs(Date startDate, Date endDate, Integer vehicleId, Pageable pageable) {
        List<FuelPurchaseLog> data = new ArrayList<>();

        if(vehicleId != null)
            data = fuelPurchaseLogRepository.findByPurchaseDateBetweenAndVehicles_Id(startDate, endDate, vehicleId, pageable);
        else
            data = fuelPurchaseLogRepository.findByPurchaseDateBetween(startDate, endDate, pageable);
        return data;
    }

    @Override
    public FuelPurchaseLog getFuelPurchaseLogById(int id) {
        return fuelPurchaseLogRepository.findOne(id);
    }

    public FuelPurchaseLog createFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog) {
        return fuelPurchaseLogRepository.save(fuelPurchaseLog);
    }

    @Override
    @Transactional
    public FuelPurchaseLog updateFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog) {
        return fuelPurchaseLogRepository.save(fuelPurchaseLog);
    }

    @Override
    public Boolean deleteFuelPurchaseLog(int id) {
        fuelPurchaseLogRepository.delete(id);
        return true;
    }
}
