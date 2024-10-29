package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.Employees;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    List<Employees> findAllByCompanyId(Integer companyId);

    List<Employees> findAllByCompanyIdAndActive(Integer companyId,
                                                boolean active,
                                                Pageable pageable);

    List<Employees> findAllByCompanyIdAndActiveAndFullNameContaining(Integer companyId,
                                                                     boolean active,
                                                                     String fullName,
                                                                     Pageable pageable);

}
