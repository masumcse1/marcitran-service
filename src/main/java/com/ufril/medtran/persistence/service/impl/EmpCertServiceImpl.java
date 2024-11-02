package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.account.EmployeeCertificateDTO;
import com.ufril.medtran.dto.account.EmployeeCertificateDTO.CertificateDTO;
import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;
import com.ufril.medtran.persistence.domain.account.Employees;
import com.ufril.medtran.persistence.repository.account.CertificateRepository;
import com.ufril.medtran.persistence.repository.account.EmployeeCertificatesRepository;
import com.ufril.medtran.persistence.repository.account.EmployeeRepository;
import com.ufril.medtran.persistence.service.EmpCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmpCertServiceImpl implements EmpCertService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        List<EmployeeCertificates> employeeCertificates = employeeCertificatesRepository.findByEmployeeID_id(id);
        employeeCertificates.forEach(empCert -> empCert.setEmployeeID(null));
        return employeeCertificates;
    }

    @Override
    @Transactional
    public void mapEmployeeCertificates(EmployeeCertificateDTO employeeCertificateDTO) {
        Employees employees = employeeRepository.findOne(employeeCertificateDTO.getEmployeeId());
        List<EmployeeCertificates> employeeCertificates = new ArrayList<>();

        for (CertificateDTO certificateDTO : employeeCertificateDTO.getCertificates()) {
            EmployeeCertificates employeeCertificate = new EmployeeCertificates();
            employeeCertificate.setEmployeeID(employees);

            Certificates certificates = certificateRepository.findOne(certificateDTO.getCertificateId());
            employeeCertificate.setCertificateID(certificates);
            employeeCertificate.setValidity(certificateDTO.getValidity());

            employeeCertificates.add(employeeCertificate);
        }
        employeeCertificatesRepository.save(employeeCertificates);
    }
}
