package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.dto.account.EmployeeDTO;
import com.ufril.medtran.persistence.domain.account.Company;
import com.ufril.medtran.persistence.domain.account.Employees;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    List<Employees> findAllByActive(boolean active, Pageable pageable);

    List<Employees> findAllByActiveAndFullNameContaining(boolean active, String  fullName, Pageable pageable);
}