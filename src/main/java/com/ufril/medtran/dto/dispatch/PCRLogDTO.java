package com.ufril.medtran.dto.dispatch;

public class PCRLogDTO {
    private int id;
    private int dispatchId;

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
    private String signature;
    private String createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
