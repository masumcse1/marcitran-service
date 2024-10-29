package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.account.Certificates;

import java.util.List;

public interface CertificateService {

    List<Certificates> getAllCertificateByCompanyId(int companyId);

    Certificates getCertificateById(int id);

    Certificates createCertificate(Certificates certificate);

    Certificates updateCertificate(Certificates certificate);

}
