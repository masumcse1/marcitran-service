package com.ufril.medtran.persistence.service;

import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.domain.common.EquipmentType;
import com.ufril.medtran.persistence.repository.common.EquipmentChecklistRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EquipmentChecklistService {
    List<EquipmentChecklistDTO> getAll(Integer shiftId, Date checkDate, int checkType);
    void save(List<EquipmentChecklist> checklist);

    List<EquipmentType> getEquipmentTypes();
}
