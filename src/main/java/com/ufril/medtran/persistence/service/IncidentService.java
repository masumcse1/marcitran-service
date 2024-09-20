package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.dispatch.FuelPurchaseLog;
import com.ufril.medtran.persistence.domain.dispatch.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IncidentService {
    List<Incident> getAllIncidents(Date startDate, Date endDate, Integer vehicleId, Pageable pageable);

    Incident getIncidentById(int id);

    Incident createIncident(Incident incident);

    Incident updateIncident(Incident incident);

    Boolean deleteIncident(int id);
}
