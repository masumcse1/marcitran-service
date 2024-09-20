package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.account.EmployeeDTO;
import com.ufril.medtran.dto.dispatch.CallsPerVehicleDTO;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.domain.account.PayRollLog;
import com.ufril.medtran.persistence.domain.common.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
  List<Employees> getAllEmployees(boolean active,String fullName, Pageable pageable);
 // List<Employees> gelAllEmployeesWithFullName(boolean active,String fullName, Pageable pageable);

    Employees getEmployeeById(int id);
    Employees createEmployee(Employees employee);
    Employees updateEmployee(Employees employee);
    boolean deleteEmployee(int id);


}
