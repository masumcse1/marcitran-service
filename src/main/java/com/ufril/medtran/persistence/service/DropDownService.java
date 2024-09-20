package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.dispatch.DispatchDTO;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.Patients;

import java.util.List;

public interface DropDownService {
    List<Dispatches> getDispatches();
    List<Shifts> getShifts();
    List<Vehicles> getVehicles();
    List<ServiceLevel> getServiceLevels();
    List<Facilities> getFacilities();
    List<Patients> getPatients();
    List<Employees> getEmployees();
}
