package com.ufril.medtran.persistence.service;


import com.ufril.medtran.dto.dispatch.FuelPurchaseLogDTO;
import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FuelPurchaseLogService {
    List<FuelPurchaseLog> getAllFuelPurchaseLogs(Date startDate, Date endDate, Integer vehicleId, Pageable pageable);
    FuelPurchaseLog getFuelPurchaseLogById(int id);
    FuelPurchaseLog createFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog) throws Exception;
    FuelPurchaseLog updateFuelPurchaseLog(FuelPurchaseLog fuelPurchaseLog);
    Boolean deleteFuelPurchaseLog(int id);
}
