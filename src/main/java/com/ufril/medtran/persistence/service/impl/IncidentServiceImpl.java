package com.ufril.medtran.persistence.service.impl;
import com.ufril.medtran.persistence.domain.dispatch.Incident;
import com.ufril.medtran.persistence.repository.dispatch.IncidentRepository;
import com.ufril.medtran.persistence.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepo;


    @Override
    public List<Incident> getAllIncidents(Date startDate, Date endDate, Integer vehicleId, Pageable pageable) {
        List<Incident> data = new ArrayList<>();
        if(vehicleId != null)
            data = incidentRepo.findByIncidentTimeBetweenAndVehicle_Id(startDate, endDate, vehicleId, pageable);
        else
            data = incidentRepo.findByIncidentTimeBetween(startDate, endDate, pageable);
        return data;
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
    public Boolean deleteIncident(int id) {
        return null;
    }
}
