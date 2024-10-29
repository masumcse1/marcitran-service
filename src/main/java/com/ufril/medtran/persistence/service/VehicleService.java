package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    List<Vehicles> getAllVehicles(Integer companyId, Pageable pageable);

    Vehicles getVehicleById(int id);

    Vehicles createVehicle(Vehicles vehicles);

    Vehicles updateVehicle(Vehicles vehicles);

    Boolean deleteVehicle(int id);
}
