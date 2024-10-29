package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journeyLogs")
public class JourneyLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Shifts shift;
    @ManyToOne
    private Vehicles vehicle;
    @ManyToOne
    private Dispatches dispatches;

    private String startFrom;
    private String destination;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date transportBeginTime;
    private int startingOdometer;
    private int endOdometer;
    private int kmCovered;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date atDestination;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date backInServiceTime;

    private String vehicleStatus;
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shifts getShift() {
        return shift;
    }

    public void setShift(Shifts shift) {
        this.shift = shift;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
    }

    public Dispatches getDispatches() {
        return dispatches;
    }

    public void setDispatches(Dispatches dispatches) {
        this.dispatches = dispatches;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
