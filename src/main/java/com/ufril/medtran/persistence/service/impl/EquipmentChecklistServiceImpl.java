package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.dto.common.EquipmentChecklistDTO;
import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.repository.common.EquipmentChecklistRepository;
import com.ufril.medtran.persistence.repository.common.EquipmentTypeRepository;
import com.ufril.medtran.persistence.service.EquipmentChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EquipmentChecklistServiceImpl implements EquipmentChecklistService {

    @Autowired
    private EquipmentChecklistRepository checklistRepo;

    @Autowired
    private EquipmentTypeRepository typeRepository;

    @Override
    public List<EquipmentChecklistDTO> getAll(Integer companyId,
                                              Integer shiftId,
                                              Date checkDate,
                                              int checkType) {

        return checklistRepo.getByCompanyIdAndShiftAndCheckDate(
                companyId, shiftId, checkDate, checkType);
    }

    @Override
    public void save(List<EquipmentChecklist> checklist) {
        checklistRepo.save(checklist);
    }

}
