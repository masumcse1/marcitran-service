package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.patient.PatientDTO;
import com.ufril.medtran.dto.patient.PatientDocumentDTO;
import com.ufril.medtran.persistence.domain.patient.DocumentType;
import com.ufril.medtran.persistence.domain.patient.PatientDocument;
import com.ufril.medtran.persistence.domain.patient.Patients;
import com.ufril.medtran.persistence.repository.patient.DocumentTypeRepository;
import com.ufril.medtran.persistence.repository.patient.PatientFileRepository;
import com.ufril.medtran.persistence.repository.patient.PatientRepository;
import com.ufril.medtran.persistence.service.PatientService;
import com.ufril.medtran.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientFileRepository documentRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    @Transactional
    public Patients createNewPatient(PatientDTO dto) {
        Patients patient = MapperUtils.mapDTOToPatient(dto);
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Patients savePatient(Patients patients) {
        return patientRepository.save(patients);
    }

    @Override
    public Patients getPatientByID(int Id) {
        return patientRepository.findOne(Id);
    }

    @Override
    public List<Patients> getAllPatients(Integer companyId,
                                         Integer status,
                                         Pageable pageable) {

        return patientRepository.findAllByCompanyIdAndStatus(
                companyId, status, pageable);
    }

    @Override
    public Patients getPatientByName(Integer companyId, String name) {
        return patientRepository.findByCompanyIdAndFirstName(companyId, name);
    }

    @Override
    @Transactional
    public void deletePatient(int id) {
        Patients patients = patientRepository.findOne(id);
        patients.setStatus(0);
        patientRepository.save(patients);
    }

    @Override
    @Transactional
    public PatientDocument saveDocument(PatientDocumentDTO documentDTO) {
        PatientDocument document = MapperUtils.mapDTOToDocument(documentDTO);
        Patients patient = getPatientByID(documentDTO.getPatientId());
        document.setPatient(patient);
        DocumentType documentType = documentTypeRepository.findByName(documentDTO.getDocumentType());
        document.setDocumentType(documentType);
        return documentRepository.save(document);
    }

    @Override
    public List<PatientDocumentDTO> getDocumentsByPatient(PatientDocumentDTO dto) {
        Patients patient = patientRepository.findOne(dto.getPatientId());
        List<PatientDocument> documents = documentRepository.findAllByPatient(patient);
        List<PatientDocumentDTO> documentDTOS = new ArrayList<>();
        for (PatientDocument document : documents) {
            documentDTOS.add(MapperUtils.mapDocumentToDTO(document));
        }
        return documentDTOS;
    }

    @Override
    public void deleteDocument(int id) {
        documentRepository.delete(id);
    }
}
