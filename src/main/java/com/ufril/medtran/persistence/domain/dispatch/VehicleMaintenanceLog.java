package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_maintenance_log")
public class VehicleMaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Vehicles vehicles;
    private boolean active;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private float odometer;
    private int task;
    @ManyToOne
    private MaintenanceType maintenanceType;
    @ManyToOne
    private RepairType repairType;
    private boolean inspectionOfVehicle;
    private boolean inspectionOfPowerLift;
    private String location;
    private float cost;
    private String notes;
    private String originalFileName;
    private String storedFileName;
    private String downloadUri;
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
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

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
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

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
