package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    List<Lead> findAllByCompanyId(int companyId);
}
