package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Affiliate;

import java.util.List;

public interface AffiliateService {

    List<Affiliate> getAllAffiliateByCompanyId(int companyId);

    Affiliate getAffiliateById(int id);

    Affiliate createAffiliate(Affiliate affiliate);

    Affiliate updateAffiliate(Affiliate affiliate);

}
