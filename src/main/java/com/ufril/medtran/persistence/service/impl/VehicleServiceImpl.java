package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import com.ufril.medtran.persistence.repository.dispatch.VehicleRepository;
import com.ufril.medtran.persistence.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicles> getAllVehicles(Integer companyId, Pageable pageable) {
        return vehicleRepository.findAllByCompanyId(companyId, pageable)
                .getContent();
    }

    @Override
    public Vehicles getVehicleById(int id) {
        return vehicleRepository.findOne(id);
    }

    @Override
    public Vehicles createVehicle(Vehicles vehicles) {
        return vehicleRepository.save(vehicles);
    }

    @Override
    public Vehicles updateVehicle(Vehicles vehicles) {
        return vehicleRepository.save(vehicles);
    }

    @Override
    public Boolean deleteVehicle(int id) {
        vehicleRepository.delete(id);
        return true;
    }
}
