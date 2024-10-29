package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.persistence.domain.common.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Integer> {

    List<EquipmentType> findAllByCheckType(int checkType);
}
