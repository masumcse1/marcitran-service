package com.ufril.medtran.persistence.domain.common;

import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.domain.dispatch.Vehicles;
import io.swagger.models.auth.In;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Entity
@Table(name = "EquipmentChecklist")
public class EquipmentChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    @ManyToOne
    private Shifts shift;
    @Column
    @Temporal(TemporalType.DATE)
    private Date checkDate;
    @ManyToOne
    private EquipmentType equipmentType;
    private Integer quantity;
    private String condition;
    private String remarks;

    private Integer expiry;
    private Integer dayStartQuantity;
    private Integer refill;
    private int companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shifts getShift() {
        return shift;
    }

    public void setShift(Shifts shift) {
        this.shift = shift;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
