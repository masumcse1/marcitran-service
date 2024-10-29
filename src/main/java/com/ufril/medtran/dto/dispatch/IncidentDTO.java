package com.ufril.medtran.dto.dispatch;

import java.util.Date;

public class IncidentDTO {

    private int id;
    private int vehicleId;
    private String callSign;
    private Date incidentTime;
    private String summary;
    private String cause;
    private int primaryActor;
    private String primaryActorName;
    private int secondaryActor;
    private String secondaryActorName;
    private String Notes;
    private int companyId;

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

    public Date getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(Date incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getPrimaryActor() {
        return primaryActor;
    }

    public void setPrimaryActor(int primaryActor) {
        this.primaryActor = primaryActor;
    }

    public String getPrimaryActorName() {
        return primaryActorName;
    }

    public void setPrimaryActorName(String primaryActorName) {
        this.primaryActorName = primaryActorName;
    }

    public int getSecondaryActor() {
        return secondaryActor;
    }

    public void setSecondaryActor(int secondaryActor) {
        this.secondaryActor = secondaryActor;
    }

    public String getSecondaryActorName() {
        return secondaryActorName;
    }

    public void setSecondaryActorName(String secondaryActorName) {
        this.secondaryActorName = secondaryActorName;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
