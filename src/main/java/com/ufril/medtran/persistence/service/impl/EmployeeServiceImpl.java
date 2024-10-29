package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.repository.account.EmployeeRepository;
import com.ufril.medtran.persistence.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employees> getAllEmployees(Integer companyId,
                                           boolean active,
                                           String fullName,
                                           Pageable pageable) {

        if (fullName != null && !fullName.isEmpty())
            return employeeRepository.findAllByCompanyIdAndActiveAndFullNameContaining(
                    companyId, active, fullName, pageable);
        else
            return employeeRepository.findAllByCompanyIdAndActive(
                    companyId, active, pageable);
    }

    @Override
    public Employees getEmployeeById(int id) {
        return employeeRepository.findOne(id);
    }

    @Override
    @Transactional
    public Employees createEmployee(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employees updateEmployee(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(int id) {
        Employees employees = employeeRepository.findOne(id);
        employees.setActive(false);
        employeeRepository.save(employees);
        return true;
    }
}
