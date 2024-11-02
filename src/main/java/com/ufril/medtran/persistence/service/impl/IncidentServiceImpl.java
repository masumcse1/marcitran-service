package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.dispatch.Incident;
import com.ufril.medtran.persistence.repository.dispatch.IncidentRepository;
import com.ufril.medtran.persistence.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepo;

    @Override
    public List<Incident> getAllIncidents(Integer companyId,
                                          Date startDate,
                                          Date endDate,
                                          Integer vehicleId,
                                          Pageable pageable) {

        if (vehicleId != null)
            return incidentRepo.findByCompanyIdAndIncidentTimeBetweenAndVehicle_Id(
                    companyId, startDate, endDate, vehicleId, pageable);
        else
            return incidentRepo.findByCompanyIdAndIncidentTimeBetween(
                    companyId, startDate, endDate, pageable);
    }

    @Override
    public Incident getIncidentById(int id) {
        return incidentRepo.findOne(id);
    }

    @Override
    public Incident createIncident(Incident incident) {
        return incidentRepo.save(incident);
    }

    @Override
    public Incident updateIncident(Incident incident) {
        return incidentRepo.save(incident);
    }

    @Override
    public void deleteIncident(int id) {
        incidentRepo.delete(id);
    }
}
