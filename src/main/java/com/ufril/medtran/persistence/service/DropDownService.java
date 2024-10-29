package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.dispatch.*;
import com.ufril.medtran.persistence.domain.patient.Patients;

import java.util.List;

public interface DropDownService {

    List<Dispatches> getDispatchesByCompanyId(Integer companyId);

    List<Shifts> getShiftsByCompanyId(Integer companyId);

    List<Vehicles> getVehiclesByCompanyId(Integer companyId);

    List<ServiceLevel> getServiceLevelsByCompanyId(Integer companyId);

    List<Facilities> getFacilitiesByCompanyId(Integer companyId);

    List<Patients> getPatientsByCompanyId(Integer companyId);

    List<Employees> getEmployeesByCompanyId(Integer companyId);
}
