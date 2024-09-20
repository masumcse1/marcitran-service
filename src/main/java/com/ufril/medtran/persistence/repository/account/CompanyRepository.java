package com.ufril.medtran.persistence.repository.account;

import com.ufril.medtran.persistence.domain.account.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
