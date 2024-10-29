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
    private DispatchRepository dispatchRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceLevelRepository serviceLevelRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Dispatches> getDispatchesByCompanyId(Integer companyId) {
        return dispatchRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Shifts> getShiftsByCompanyId(Integer companyId) {
        return shiftRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Vehicles> getVehiclesByCompanyId(Integer companyId) {
        return vehicleRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<ServiceLevel> getServiceLevelsByCompanyId(Integer companyId) {
        return serviceLevelRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Facilities> getFacilitiesByCompanyId(Integer companyId) {
        return facilityRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Patients> getPatientsByCompanyId(Integer companyId) {
        return patientRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Employees> getEmployeesByCompanyId(Integer companyId) {
        return employeeRepository.findAllByCompanyId(companyId);
    }
}
