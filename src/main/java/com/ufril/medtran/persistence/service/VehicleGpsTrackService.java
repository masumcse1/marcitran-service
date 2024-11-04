package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.VehicleGpsTrack;

import java.util.Date;
import java.util.List;

public interface VehicleGpsTrackService {

    void save(VehicleGpsTrack vehicleGpsTrack);

    List<VehicleGpsTrack> getAll(String deviceId, Date startDate, Date endDate);
}
