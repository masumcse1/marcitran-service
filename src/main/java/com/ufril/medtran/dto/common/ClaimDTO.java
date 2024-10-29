package com.ufril.medtran.dto.common;

import com.ufril.medtran.persistence.domain.common.Claim;

import java.util.Date;

public class ClaimDTO {

    public ClaimDTO() {
    }

    public ClaimDTO(Claim claim) {
        this.setId(claim.getId());
        this.setClaimDate(claim.getClaimDate());
        this.setClaimType(claim.getClaimType());
        this.setReason(claim.getReason());
        this.setInTime(claim.getInTime());
        this.setOutTime(claim.getOutTime());
        this.setNextApprover(claim.getNextApprover());
        this.setCreatedBy(claim.getCreatedBy());
        this.setApprovalStatus(claim.getApprovalStatus());
        this.setNotes(claim.getNotes());
        this.setName(claim.getName());
        this.setHourlyRate(claim.getHourlyRate());
        this.setCompanyId(claim.getCompanyId());

        if (claim.getVehicles() != null) {
            this.setVehicleId(claim.getVehicles().getId());
            this.setVehicleCallSign(claim.getVehicles().getCallSign());
        }
    }

    private int id;

    private Date claimDate;
    private int claimType;
    private String reason;

    private int vehicleId;
    private String vehicleCallSign;

    private int dispatchId;

    private Date inTime;
    private Date outTime;
    private float totalHours;
    private String nextApprover;
    private String createdBy;
    private Integer ApprovalStatus;
    private String Notes;

    private String name;
    private float hourlyRate;
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public int getClaimType() {
        return claimType;
    }

    public void setClaimType(int claimType) {
        this.claimType = claimType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleCallSign() {
        return vehicleCallSign;
    }

    public void setVehicleCallSign(String vehicleCallSign) {
        this.vehicleCallSign = vehicleCallSign;
    }

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public String getNextApprover() {
        return nextApprover;
    }

    public void setNextApprover(String nextApprover) {
        this.nextApprover = nextApprover;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
