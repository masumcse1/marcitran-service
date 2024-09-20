package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.Facilities;
import java.util.List;

public interface FacilityService {
    List<Facilities> getAllFacilities();
    Facilities getFacilitiesById(int id);
    Facilities getFacilityByName(String name);
    Facilities createFacilities(Facilities Facilities);
    Facilities updateFacilities(Facilities Facilities);
    Boolean deleteFacilities(int id);
}
