package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.domain.common.EquipmentType;

import java.util.Date;
import java.util.List;

public interface EquipmentChecklistService {

    List<EquipmentChecklistDTO> getAll(Integer companyId,
                                       Integer shiftId,
                                       Date checkDate,
                                       int checkType);

    void save(List<EquipmentChecklist> checklist);

    List<EquipmentType> getEquipmentTypes(Integer companyId);
}
