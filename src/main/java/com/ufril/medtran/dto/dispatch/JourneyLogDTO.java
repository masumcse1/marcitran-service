package com.ufril.medtran.dto.dispatch;

import java.util.Date;

public class JourneyLogDTO {

    private int id;
    private int shiftId;
    private int vehicleId;
    private String callSign;
    private int dispatchId;
    private String startFrom;
    private String destination;
    private Date transportBeginTime;
    private int startingOdometer;
    private Date atDestination;
    private int kmCovered;
    private int endOdometer;
    private Date backInServiceTime;
    private String vehicleStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
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

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTransportBeginTime() {
        return transportBeginTime;
    }

    public void setTransportBeginTime(Date transportBeginTime) {
        this.transportBeginTime = transportBeginTime;
    }

    public int getStartingOdometer() {
        return startingOdometer;
    }

    public void setStartingOdometer(int startingOdometer) {
        this.startingOdometer = startingOdometer;
    }

    public Date getAtDestination() {
        return atDestination;
    }

    public void setAtDestination(Date atDestination) {
        this.atDestination = atDestination;
    }

    public int getKmCovered() {
        return kmCovered;
    }

    public void setKmCovered(int kmCovered) {
        this.kmCovered = kmCovered;
    }

    public int getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(int endOdometer) {
        this.endOdometer = endOdometer;
    }

    public Date getBackInServiceTime() {
        return backInServiceTime;
    }

    public void setBackInServiceTime(Date backInServiceTime) {
        this.backInServiceTime = backInServiceTime;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }
}
