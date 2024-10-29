package com.ufril.medtran.persistence.repository.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findByCompanyIdAndIncidentTimeBetween(Integer companyId,
                                                         Date startDate,
                                                         Date endDate,
                                                         Pageable pageable);

    List<Incident> findByCompanyIdAndIncidentTimeBetweenAndVehicle_Id(Integer companyId,
                                                                      Date startDate,
                                                                      Date endDate,
                                                                      Integer vehicleId,
                                                                      Pageable pageable);
}
