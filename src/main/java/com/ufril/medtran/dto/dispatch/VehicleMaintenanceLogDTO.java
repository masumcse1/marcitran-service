package com.ufril.medtran.dto.dispatch;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class VehicleMaintenanceLogDTO {
    private int id;
    private int vehicleId;
    private String callSign;
    private boolean active;
    private Date date;
    private float odometer;
    private int task;
    private int maintenanceTypeId;
    private int repairTypeId;
    private boolean inspectionOfVehicle;
    private boolean inspectionOfPowerLift;
    private String location;
    private float cost;
    private String notes;
    private String downloadUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getOdometer() {
        return odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getMaintenanceTypeId() {
        return maintenanceTypeId;
    }

    public void setMaintenanceTypeId(int maintenanceTypeId) {
        this.maintenanceTypeId = maintenanceTypeId;
    }

    public int getRepairTypeId() {
        return repairTypeId;
    }

    public void setRepairTypeId(int repairTypeId) {
        this.repairTypeId = repairTypeId;
    }

    public boolean isInspectionOfVehicle() {
        return inspectionOfVehicle;
    }

    public void setInspectionOfVehicle(boolean inspectionOfVehicle) {
        this.inspectionOfVehicle = inspectionOfVehicle;
    }

    public boolean isInspectionOfPowerLift() {
        return inspectionOfPowerLift;
    }

    public void setInspectionOfPowerLift(boolean inspectionOfPowerLift) {
        this.inspectionOfPowerLift = inspectionOfPowerLift;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
