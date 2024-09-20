package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType,Integer> {
    List<EquipmentType> findByCheckType(@Param("checkType") int checkType);
}
