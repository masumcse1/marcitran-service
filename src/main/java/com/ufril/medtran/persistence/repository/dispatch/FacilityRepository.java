package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facilities, Integer> {

    Facilities findByCompanyIdAndName(int companyId, String name);

    List<Facilities> findAllByCompanyId(Integer companyId);

}
