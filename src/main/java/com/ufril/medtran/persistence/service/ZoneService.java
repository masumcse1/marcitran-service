package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAllZone();
    Zone getZoneById(int id);
    Zone createZone(Zone zone);
    Zone updateZone(Zone zone);
    Boolean deleteZone(int id);
}
