package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.Zone;
import com.ufril.medtran.persistence.repository.dispatch.ZoneRepository;
import com.ufril.medtran.persistence.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public List<Zone> getAllZone() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone getZoneById(int id) {
        return zoneRepository.findOne(id);
    }

    @Override
    @Transactional
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    @Transactional
    public Zone updateZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Boolean deleteZone(int id) {
        zoneRepository.delete(id);
        return true;
    }
}
