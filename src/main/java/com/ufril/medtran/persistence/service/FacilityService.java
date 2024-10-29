package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.Facilities;

import java.util.List;

public interface FacilityService {

    List<Facilities> getAllFacilities(int companyId);

    Facilities getFacilitiesById(int id);

    Facilities getFacilityByName(int companyId, String name);

    Facilities createFacilities(Facilities Facilities);

    Facilities updateFacilities(Facilities Facilities);

    Boolean deleteFacilities(int id);
}
