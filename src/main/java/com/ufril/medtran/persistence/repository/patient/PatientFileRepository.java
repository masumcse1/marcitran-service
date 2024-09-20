package com.ufril.medtran.persistence.repository.patient;

import com.ufril.medtran.persistence.domain.patient.PatientDocument;
import com.ufril.medtran.persistence.domain.patient.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientFileRepository extends JpaRepository<PatientDocument, Integer> {

	List<PatientDocument> findAllByPatient(Patients patient);
}
