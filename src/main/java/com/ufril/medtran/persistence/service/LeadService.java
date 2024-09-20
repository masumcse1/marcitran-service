package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Affiliate;
import com.ufril.medtran.persistence.domain.common.Lead;

import java.util.List;

public interface LeadService {
    List<Lead> getAllLead();
    Lead getLeadById(int id);
    Lead createLead(Lead lead);
    Lead updateLead(Lead lead);
}
