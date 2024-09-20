package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.ServiceLevel;

import java.util.List;

public interface ServiceLevelService {
    List<ServiceLevel> getAllServiceLevels();
    ServiceLevel getServiceLevelById(int id);
    ServiceLevel createServiceLevel(ServiceLevel ServiceLevel);
    ServiceLevel updateServiceLevel(ServiceLevel ServiceLevel);
    Boolean deleteServiceLevel(int id);
}
