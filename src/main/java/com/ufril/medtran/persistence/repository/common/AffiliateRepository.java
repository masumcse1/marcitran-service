package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.dto.report.PortfolioDTO;
import com.ufril.medtran.persistence.domain.common.Affiliate;
import com.ufril.medtran.persistence.domain.common.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Integer> {

    //@Query("")
    //int getCustomerRetention();
}
