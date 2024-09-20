package com.ufril.medtran.dto.dispatch;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class ShiftDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String basedFromLocation;
    private String postingLocation;
    private String primaryRole;
    private Date startTime;
    private Date endTime;
    private float expectedLength;
    private String primaryCheckList;
    private String secondaryCheckList;
    private int vehicle;
    private String vehicleCallSign;

    private int effServiceLevel;
    private String serviceLevelName;

    private float startingOdometer;
    private float endOdometer;
    private Integer status;
    private String fuelLevel;
    private String shiftType;

    private List<Integer> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBasedFromLocation() {
        return basedFromLocation;
    }

    public void setBasedFromLocation(String basedFromLocation) {
        this.basedFromLocation = basedFromLocation;
    }

    public String getPostingLocation() {
        return postingLocation;
    }

    public void setPostingLocation(String postingLocation) {
        this.postingLocation = postingLocation;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public float getExpectedLength() {
        return expectedLength;
    }

    public void setExpectedLength(float expectedLength) {
        this.expectedLength = expectedLength;
    }

    public String getPrimaryCheckList() {
        return primaryCheckList;
    }

    public void setPrimaryCheckList(String primaryCheckList) {
        this.primaryCheckList = primaryCheckList;
    }

    public String getSecondaryCheckList() {
        return secondaryCheckList;
    }

    public void setSecondaryCheckList(String secondaryCheckList) {
        this.secondaryCheckList = secondaryCheckList;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getEffServiceLevel() {
        return effServiceLevel;
    }

    public void setEffServiceLevel(int effServiceLevel) {
        this.effServiceLevel = effServiceLevel;
    }

    public float getStartingOdometer() {
        return startingOdometer;
    }

    public void setStartingOdometer(float startingOdometer) {
        this.startingOdometer = startingOdometer;
    }

    public float getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(float endOdometer) {
        this.endOdometer = endOdometer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public List<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Integer> employees) {
        this.employees = employees;
    }

    public String getVehicleCallSign() {
        return vehicleCallSign;
    }

    public void setVehicleCallSign(String vehicleCallSign) {
        this.vehicleCallSign = vehicleCallSign;
    }

    public String getServiceLevelName() {
        return serviceLevelName;
    }

    public void setServiceLevelName(String serviceLevelName) {
        this.serviceLevelName = serviceLevelName;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
}
