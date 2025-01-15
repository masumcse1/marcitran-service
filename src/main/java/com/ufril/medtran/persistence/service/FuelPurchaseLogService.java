package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FuelPurchaseLogService {

    List<FuelPurchaseLog> getAllFuelPurchaseLogs(Integer companyId,
                                                 Date startDate,
                                                 Date endDate,
                                                 Integer vehicleId,
                                                 Pageable pageable);

    FuelPurchaseLog getFuelPurchaseLogById(int id);

    FuelPurchaseLog createFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog) throws Exception;

    void updateFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog);

    Boolean deleteFuelPurchaseLog(int id);
}
