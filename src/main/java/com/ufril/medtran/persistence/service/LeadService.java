package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Lead;

import java.util.List;

public interface LeadService {

    List<Lead> getAllLead(Integer companyId);

    Lead getLeadById(int id);

    Lead createLead(Lead lead);

    Lead updateLead(Lead lead);
}
