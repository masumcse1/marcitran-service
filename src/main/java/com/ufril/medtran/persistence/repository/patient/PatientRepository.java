package com.ufril.medtran.persistence.repository.patient;

import com.ufril.medtran.dto.report.PortfolioDTO;
import com.ufril.medtran.persistence.domain.patient.Patients;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Integer> {

    List<Patients> findAllByStatus(Integer status, Pageable pageable);
    Patients findByFirstName(String name);

    //@Query("")
    //List<PortfolioDTO> getPortfolioGrowth();
}
