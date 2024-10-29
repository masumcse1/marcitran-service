package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.ServiceLevel;
import com.ufril.medtran.persistence.repository.dispatch.ServiceLevelRepository;
import com.ufril.medtran.persistence.service.ServiceLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceLevelServiceImpl implements ServiceLevelService {

    @Autowired
    private ServiceLevelRepository serviceLevelRepository;

    @Override
    public List<ServiceLevel> getAllServiceLevels(Integer companyId) {
        return serviceLevelRepository.findAllByCompanyId(companyId);
    }

    @Override
    public ServiceLevel getServiceLevelById(int id) {
        return serviceLevelRepository.findOne(id);
    }

    @Override
    @Transactional
    public ServiceLevel createServiceLevel(ServiceLevel ServiceLevel) {
        return serviceLevelRepository.save(ServiceLevel);
    }

    @Override
    @Transactional
    public ServiceLevel updateServiceLevel(ServiceLevel ServiceLevel) {
        return serviceLevelRepository.save(ServiceLevel);
    }

    @Override
    public Boolean deleteServiceLevel(int id) {
        serviceLevelRepository.delete(id);
        return true;
    }
}
