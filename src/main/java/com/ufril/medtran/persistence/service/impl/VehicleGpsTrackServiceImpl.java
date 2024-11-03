package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.VehicleGpsTrack;
import com.ufril.medtran.persistence.repository.dispatch.VehicleGpsTrackRepository;
import com.ufril.medtran.persistence.service.VehicleGpsTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class VehicleGpsTrackServiceImpl implements VehicleGpsTrackService {

    @Autowired
    private VehicleGpsTrackRepository vehicleGpsTrackRepository;

    @Async
    @Override
    public void save(VehicleGpsTrack vehicleGpsTrack) {
        vehicleGpsTrackRepository.save(vehicleGpsTrack);
    }
}
