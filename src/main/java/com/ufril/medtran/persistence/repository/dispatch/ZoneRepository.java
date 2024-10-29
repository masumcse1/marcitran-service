package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

    List<Zone> findAllByCompanyId(Integer companyId);

}
