package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCertificatesRepository extends JpaRepository<EmployeeCertificates, Integer> {

    List<EmployeeCertificates> findByEmployeeID(int employeeId);
}
