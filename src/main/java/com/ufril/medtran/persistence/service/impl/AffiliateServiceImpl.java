package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.common.Affiliate;
import com.ufril.medtran.persistence.repository.common.AffiliateRepository;
import com.ufril.medtran.persistence.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AffiliateServiceImpl implements AffiliateService {

    @Autowired
    private AffiliateRepository affiliateRepository;

    @Override
    public List<Affiliate> getAllAffiliateByCompanyId(int companyId) {
        return affiliateRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Affiliate getAffiliateById(int id) {
        return affiliateRepository.findOne(id);
    }

    @Override
    public Affiliate createAffiliate(Affiliate affiliate) {
        return affiliateRepository.save(affiliate);
    }

    @Override
    public Affiliate updateAffiliate(Affiliate affiliate) {
        return affiliateRepository.save(affiliate);
    }
}
