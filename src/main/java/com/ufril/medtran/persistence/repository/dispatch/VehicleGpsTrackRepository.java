package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.VehicleGpsTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleGpsTrackRepository extends CrudRepository<VehicleGpsTrack, Long> {

    List<VehicleGpsTrack> findAllByDeviceIdAndTrackAtBetween(
            String deviceId, Date startDate, Date endDate);
}
