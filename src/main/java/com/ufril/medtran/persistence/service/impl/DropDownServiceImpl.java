package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.Patients;
import com.ufril.medtran.persistence.repository.account.EmployeeRepository;
import com.ufril.medtran.persistence.repository.dispatch.*;
import com.ufril.medtran.persistence.repository.patient.PatientRepository;
import com.ufril.medtran.persistence.service.DropDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DropDownServiceImpl implements DropDownService {
    @Autowired
    private DispatchRepository dispatchRepo;
    @Autowired
    private ShiftRepository shiftRepo;
    @Autowired
    private VehicleRepository vehicleRepo;
    @Autowired
    private ServiceLevelRepository serviceLevelRepo;
    @Autowired
    private FacilityRepository facilityRepo;
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public List<Dispatches> getDispatches() {
        return dispatchRepo.findAll();
    }

    @Override
    public List<Shifts> getShifts() {
        return shiftRepo.findAll();
    }

    @Override
    public List<Vehicles> getVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public List<ServiceLevel> getServiceLevels() {
        return serviceLevelRepo.findAll();
    }

    @Override
    public List<Facilities> getFacilities() {
        return facilityRepo.findAll();
    }

    @Override
    public List<Patients> getPatients() {
        return patientRepo.findAll();
    }

    @Override
    public List<Employees> getEmployees() {
        return employeeRepo.findAll();
    }
}
