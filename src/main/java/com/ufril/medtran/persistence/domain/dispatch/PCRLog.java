package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pcr_log")
public class PCRLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "dispatchId", referencedColumnName = "id", nullable = false)
    private Dispatches dispatch;

    private String assessment;
    private String ecgRecord;
    private String intervention;
    private String icActivity;
    private String labTest;
    private String medication;
    private String miReport;
    private String procedure;
    private String vital;

    private String details;

    @Lob
    private byte[] signature;
    private String createdBy;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dispatches getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatches dispatch) {
        this.dispatch = dispatch;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getEcgRecord() {
        return ecgRecord;
    }

    public void setEcgRecord(String ecgRecord) {
        this.ecgRecord = ecgRecord;
    }

    public String getIntervention() {
        return intervention;
    }

    public void setIntervention(String intervention) {
        this.intervention = intervention;
    }

    public String getIcActivity() {
        return icActivity;
    }

    public void setIcActivity(String icActivity) {
        this.icActivity = icActivity;
    }

    public String getLabTest() {
        return labTest;
    }

    public void setLabTest(String labTest) {
        this.labTest = labTest;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMiReport() {
        return miReport;
    }

    public void setMiReport(String miReport) {
        this.miReport = miReport;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getVital() {
        return vital;
    }

    public void setVital(String vital) {
        this.vital = vital;
    }

    public Date getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(Date submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
