package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.VehicleGpsTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleGpsTrackRepository extends CrudRepository<VehicleGpsTrack, Long> {
}
