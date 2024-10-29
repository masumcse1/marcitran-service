package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.Certificates;
import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;

import java.util.List;

public interface EmpCertService {

    List<Certificates> getAllCertificates();

    List<EmployeeCertificates> getCertificatesByEmployeeId(int id);

    List<EmployeeCertificates> mapEmployeeCertificates(List<EmployeeCertificates> empCertList);
}
