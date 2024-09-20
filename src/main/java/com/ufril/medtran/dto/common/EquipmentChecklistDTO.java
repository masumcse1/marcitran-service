package com.ufril.medtran.dto.common;

import com.ufril.medtran.persistence.domain.common.EquipmentChecklist;
import com.ufril.medtran.persistence.domain.common.EquipmentType;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;

import javax.persistence.*;
import java.util.Date;

public class EquipmentChecklistDTO {
    public EquipmentChecklistDTO() {

    }

    public  EquipmentChecklistDTO(EquipmentChecklist checklist) {
        this.setId(checklist.getId());
        this.setShiftId(checklist.getShift().getId());
        this.setCheckDate(checklist.getCheckDate());
        this.setEquipmentTypeId(checklist.getEquipmentType().getId());
        this.setEquipmentTypeName(checklist.getEquipmentType().getName());
        this.setQuantity(checklist.getQuantity());
        this.setCondition(checklist.getCondition());
        this.setRemarks(checklist.getRemarks());

        this.setExpiry(checklist.getExpiry());
        this.setDayStartQuantity(checklist.getDayStartQuantity());
        this.setRefill(checklist.getRefill());
    }

    private  Integer id;
    private Integer shiftId;
    private Date checkDate;
    private Integer equipmentTypeId;
    private String equipmentTypeName;
    private Integer quantity;
    private String condition;
    private String remarks;

    private Integer expiry;
    private Integer dayStartQuantity;
    private Integer refill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(Integer equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDayStartQuantity() {
        return dayStartQuantity;
    }

    public void setDayStartQuantity(Integer dayStartQuantity) {
        this.dayStartQuantity = dayStartQuantity;
    }

    public Integer getRefill() {
        return refill;
    }

    public void setRefill(Integer refill) {
        this.refill = refill;
    }

    public Integer getExpiry() {
        return expiry;
    }

    public void setExpiry(Integer expiry) {
        this.expiry = expiry;
    }
}
