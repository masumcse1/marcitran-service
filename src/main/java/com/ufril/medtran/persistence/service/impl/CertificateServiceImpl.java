package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.repository.account.CertificateRepository;
import com.ufril.medtran.persistence.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public List<Certificates> getAllCertificateByCompanyId(int companyId) {
        return certificateRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Certificates getCertificateById(int id) {
        return certificateRepository.findOne(id);
    }

    @Override
    public Certificates createCertificate(Certificates certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificates updateCertificate(Certificates certificate) {
        return certificateRepository.save(certificate);
    }
}
