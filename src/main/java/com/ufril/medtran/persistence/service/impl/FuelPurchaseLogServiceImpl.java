package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.repository.dispatch.FuelPurchaseLogRepository;
import com.ufril.medtran.persistence.service.FuelPurchaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FuelPurchaseLogServiceImpl implements FuelPurchaseLogService {

    @Autowired
    private FuelPurchaseLogRepository fuelPurchaseLogRepository;

    @Override
    public List<FuelPurchaseLog> getAllFuelPurchaseLogs(Integer companyId,
                                                        Date startDate,
                                                        Date endDate,
                                                        Integer vehicleId,
                                                        Pageable pageable) {

        if (vehicleId != null) {
            if (startDate != null && endDate != null) {
                return fuelPurchaseLogRepository.findByCompanyIdAndPurchaseDateBetweenAndVehicles_Id(
                        companyId, startDate, endDate, vehicleId, pageable);
            } else {
                return fuelPurchaseLogRepository.findByCompanyIdAndVehicles_Id(
                        companyId, vehicleId, pageable);
            }
        } else {
            if (startDate != null && endDate != null) {
                return fuelPurchaseLogRepository.findByCompanyIdAndPurchaseDateBetween(
                        companyId, startDate, endDate, pageable);
            } else {
                return fuelPurchaseLogRepository.findByCompanyId(companyId, pageable);
            }
        }
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
    public void updateFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog) {
        fuelPurchaseLogRepository.save(fuelPurchaseLog);
    }

    @Override
    public Boolean deleteFuelPurchaseLog(int id) {
        fuelPurchaseLogRepository.delete(id);
        return true;
    }
}
