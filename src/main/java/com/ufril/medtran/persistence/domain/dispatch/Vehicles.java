package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String callSign;
    @ManyToOne
    private VehicleType vehicleType;
    @ManyToOne
    private ServiceLevel serviceLevel;
    private int patientCapacity;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheckOff;
    private String nemsisID;
    private String station;
    private int fuelTankRange;
    private String viin;
    private String permitorLicence;
    private String purchasePrice;
    private float odometer;
    private String beaconAppToken;
    private String phone;
    private String primaryMessageType;
    private String primaryMessageAddress;
    private String secondaryMessageType;
    private String secondaryMessageAddress;
    private String notes;
    private String status;
    private String deviceId;
    private int companyId;

//	private VehicleMaintenanceLog vehicleMaintenanceLog;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ServiceLevel getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(ServiceLevel serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public int getPatientCapacity() {
        return patientCapacity;
    }

    public void setPatientCapacity(int patientCapacity) {
        this.patientCapacity = patientCapacity;
    }

    public Date getLastCheckOff() {
        return lastCheckOff;
    }

    public void setLastCheckOff(Date lastCheckOff) {
        this.lastCheckOff = lastCheckOff;
    }

    public String getNemsisID() {
        return nemsisID;
    }

    public void setNemsisID(String nemsisID) {
        this.nemsisID = nemsisID;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getFuelTankRange() {
        return fuelTankRange;
    }

    public void setFuelTankRange(int fuelTankRange) {
        this.fuelTankRange = fuelTankRange;
    }

    public String getViin() {
        return viin;
    }

    public void setViin(String viin) {
        this.viin = viin;
    }

    public String getPermitorLicence() {
        return permitorLicence;
    }

    public void setPermitorLicence(String permitorLicence) {
        this.permitorLicence = permitorLicence;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getOdometer() {
        return odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public String getBeaconAppToken() {
        return beaconAppToken;
    }

    public void setBeaconAppToken(String beaconAppToken) {
        this.beaconAppToken = beaconAppToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrimaryMessageType() {
        return primaryMessageType;
    }

    public void setPrimaryMessageType(String primaryMessageType) {
        this.primaryMessageType = primaryMessageType;
    }

    public String getPrimaryMessageAddress() {
        return primaryMessageAddress;
    }

    public void setPrimaryMessageAddress(String primaryMessageAddress) {
        this.primaryMessageAddress = primaryMessageAddress;
    }

    public String getSecondaryMessageType() {
        return secondaryMessageType;
    }

    public void setSecondaryMessageType(String secondaryMessageType) {
        this.secondaryMessageType = secondaryMessageType;
    }

    public String getSecondaryMessageAddress() {
        return secondaryMessageAddress;
    }

    public void setSecondaryMessageAddress(String secondaryMessageAddress) {
        this.secondaryMessageAddress = secondaryMessageAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
