package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.patient.PatientDTO;
import com.ufril.medtran.dto.patient.PatientDocumentDTO;
import com.ufril.medtran.persistence.domain.patient.PatientDocument;
import com.ufril.medtran.persistence.domain.patient.Patients;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    Patients createNewPatient(PatientDTO dto);

    Patients savePatient(Patients patients);

    Patients getPatientByID(int Id);

    List<Patients> getAllPatients(Integer companyId, Integer status, Pageable pageable);

    Patients getPatientByName(Integer companyId, String name);

    void deletePatient(int id);

    PatientDocument saveDocument(PatientDocumentDTO documentDTO);

    List<PatientDocumentDTO> getDocumentsByPatient(PatientDocumentDTO dto);

    void deleteDocument(int id);
}
