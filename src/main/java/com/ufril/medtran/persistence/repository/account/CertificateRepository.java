package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificates, Integer> {

    List<Certificates> findAllByCompanyId(int companyId);

}
