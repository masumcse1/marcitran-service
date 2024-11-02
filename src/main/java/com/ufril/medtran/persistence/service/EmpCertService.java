package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.account.EmployeeCertificateDTO;
import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;

import java.util.List;

public interface EmpCertService {

    List<Certificates> getAllCertificates();

    List<EmployeeCertificates> getCertificatesByEmployeeId(int id);

    void mapEmployeeCertificates(EmployeeCertificateDTO employeeCertificateDTO);
}
