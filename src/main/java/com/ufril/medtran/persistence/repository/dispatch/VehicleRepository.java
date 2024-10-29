package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {

    Page<Vehicles> findAllByCompanyId(Integer companyId, Pageable pageable);

    List<Vehicles> findAllByCompanyId(Integer companyId);

}
