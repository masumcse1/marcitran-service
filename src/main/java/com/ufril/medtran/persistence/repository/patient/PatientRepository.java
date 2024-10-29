package com.ufril.medtran.persistence.repository.patient;

import com.ufril.medtran.persistence.domain.patient.Patients;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Integer> {

    Patients findByCompanyIdAndFirstName(Integer companyId, String name);

    List<Patients> findAllByCompanyId(Integer companyId);

    List<Patients> findAllByCompanyIdAndStatus(Integer companyId,
                                               Integer status,
                                               Pageable pageable);

}
