package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import com.ufril.medtran.persistence.repository.account.CertificateRepository;
import com.ufril.medtran.persistence.repository.account.EmployeeCertificatesRepository;
import com.ufril.medtran.persistence.service.EmpCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpCertServiceImpl implements EmpCertService {

    @Autowired
    private EmployeeCertificatesRepository employeeCertificatesRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public List<Certificates> getAllCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public List<EmployeeCertificates> getCertificatesByEmployeeId(int id) {
        return employeeCertificatesRepository.findByEmployeeID(id);
    }

    @Override
    @Transactional
    public List<EmployeeCertificates> mapEmployeeCertificates(List<EmployeeCertificates> empCertList) {
        return employeeCertificatesRepository.save(empCertList);
    }
}
