package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.common.Affiliate;
import com.ufril.medtran.persistence.domain.common.Lead;
import com.ufril.medtran.persistence.repository.common.AffiliateRepository;
import com.ufril.medtran.persistence.repository.common.LeadRepository;
import com.ufril.medtran.persistence.service.AffiliateService;
import com.ufril.medtran.persistence.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public List<Lead> getAllLead() {
        return leadRepository.findAll();
    }

    @Override
    public Lead getLeadById(int id) {
        return leadRepository.findOne(id);
    }

    @Override
    public Lead createLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public Lead updateLead(Lead lead) {
        return leadRepository.save(lead);
    }
}
