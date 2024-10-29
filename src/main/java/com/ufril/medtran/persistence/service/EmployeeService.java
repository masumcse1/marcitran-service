package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.Employees;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<Employees> getAllEmployees(Integer companyId,
                                    boolean active,
                                    String fullName,
                                    Pageable pageable);

    Employees getEmployeeById(int id);

    Employees createEmployee(Employees employee);

    Employees updateEmployee(Employees employee);

    boolean deleteEmployee(int id);

}
