package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FuelPurchaseLogRepository extends JpaRepository<FuelPurchaseLog, Integer> {

    List<FuelPurchaseLog> findByCompanyIdAndPurchaseDateBetween(Integer companyId,
                                                                Date startDate,
                                                                Date endDate,
                                                                Pageable pageable);

    List<FuelPurchaseLog> findByCompanyIdAndPurchaseDateBetweenAndVehicles_Id(Integer companyId,
                                                                              Date startDate,
                                                                              Date endDate,
                                                                              int vehicleId,
                                                                              Pageable pageable);
}
