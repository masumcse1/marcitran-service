package com.ufril.medtran.persistence.repository.patient;

import com.ufril.medtran.persistence.domain.patient.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

	DocumentType findByName(String name);
}
