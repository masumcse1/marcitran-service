package com.ufril.medtran.persistence.domain.dispatch;

import com.ufril.medtran.persistence.domain.account.Employees;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Vehicles vehicle;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date incidentTime;
    private String summary;
    private String cause;
    @ManyToOne
    private Employees primaryActor;
    @ManyToOne
    private Employees secondaryActor;
    private String Notes;
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle) {
        this.vehicle = vehicle;
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

    public Employees getPrimaryActor() {
        return primaryActor;
    }

    public void setPrimaryActor(Employees primaryActor) {
        this.primaryActor = primaryActor;
    }

    public Employees getSecondaryActor() {
        return secondaryActor;
    }

    public void setSecondaryActor(Employees secondaryActor) {
        this.secondaryActor = secondaryActor;
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
