package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.Facilities;
import com.ufril.medtran.persistence.repository.dispatch.FacilityRepository;
import com.ufril.medtran.persistence.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facilities> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Facilities getFacilityByName(String name) {
        return facilityRepository.findByName(name);
    }

    @Override
    public Facilities getFacilitiesById(int id) {
        return facilityRepository.findOne(id);
    }

    @Override
    @Transactional
    public Facilities createFacilities(Facilities facilities) {
        return facilityRepository.save(facilities);
    }

    @Override
    @Transactional
    public Facilities updateFacilities(Facilities facilities) {
        return facilityRepository.save(facilities);
    }

    @Override
    public Boolean deleteFacilities(int id) {
        facilityRepository.delete(id);
        return true;
    }
}
