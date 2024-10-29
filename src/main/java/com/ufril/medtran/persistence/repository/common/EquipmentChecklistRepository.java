package com.ufril.medtran.persistence.repository.common;

import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EquipmentChecklistRepository extends JpaRepository<EquipmentChecklist, Integer> {

    @Query("SELECT new com.ufril.medtran.dto.common.EquipmentChecklistDTO(e) " +
            "FROM EquipmentChecklist e " +
            "WHERE e.companyId = :companyId " +
            "AND e.shift.id = :shiftId " +
            "AND e.checkDate >= :checkDate " +
            "AND e.equipmentType.checkType = :checkType ")
    List<EquipmentChecklistDTO> getByCompanyIdAndShiftAndCheckDate(@Param("companyId") int companyId,
                                                                   @Param("shiftId") int shiftId,
                                                                   @Param("checkDate") Date checkDate,
                                                                   @Param("checkType") int checkType);
}
