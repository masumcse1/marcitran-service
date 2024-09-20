package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.report.PortfolioDTO;
import com.ufril.medtran.persistence.repository.common.AffiliateRepository;
import com.ufril.medtran.persistence.repository.patient.PatientRepository;
import com.ufril.medtran.persistence.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private AffiliateRepository affiliateRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PortfolioDTO> getPortfolioGrowth(int currentYear, int pastYear) {
        //return patientRepository.getPortfolioGrowth();
        return new ArrayList<>();
    }

    @Override
    public int getCustomerRetention() {
        //return affiliateRepository.getCustomerRetention();
        return 0;
    }
}
