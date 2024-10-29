package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.Affiliate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Integer> {

    List<Affiliate> findAllByCompanyId(int companyId);

}
