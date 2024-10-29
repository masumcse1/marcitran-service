package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.ServiceLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLevelRepository extends JpaRepository<ServiceLevel, Integer> {

    List<ServiceLevel> findAllByCompanyId(Integer companyId);

}
