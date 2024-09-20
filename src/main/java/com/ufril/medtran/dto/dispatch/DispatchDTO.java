package com.ufril.medtran.dto.dispatch;

import com.ufril.medtran.persistence.domain.dispatch.Facilities;
import com.ufril.medtran.persistence.domain.dispatch.ServiceLevel;
import com.ufril.medtran.persistence.domain.dispatch.Shifts;
import com.ufril.medtran.persistence.domain.patient.Patients;

import java.util.Date;

public class DispatchDTO {
    public DispatchDTO(){

    }

    public DispatchDTO(int id, String caller, String phone,
                       Facilities origin, Facilities destination,
                       Patients patient, ServiceLevel serviceLevel, Shifts shift, String createdBy, Date createdDate){
        this.setId(id);
        this.setCaller(caller);
        this.setPhone(phone);

        if(origin != null)
            this.setOriginAddress(origin.getName());
        if(destination != null)
            this.setDestinationAddress(destination.getName());
        if(patient != null)
            this.setPatientName(patient.getFirstName() + " " + patient.getLastName());
        if(serviceLevel != null)
            this.setServiceLevelName(serviceLevel.getName());
        if(shift != null && shift.getVehicle() != null)
            this.setShiftInfo(shift.getVehicle().getCallSign() + " " + shift.getShiftType());
        this.setCreatedBy(createdBy);
        this.setCreatedDate(createdDate);
    }

    private int id;

    private String caller;
    private String phone;
    private int tag;

    private int origin;
    private String originAddress;

    private int Destination;
    private String destinationAddress;

    private String reason;

    private int patient;
    private String patientName;

    private int serviceLevel;
    private String serviceLevelName;

    private String complaint;
    private boolean isBillable;
    private int priority;
    private boolean priorAuth;
    private float billToInsurance;
    private float billToFacility;
    private float billToAffiliate;
    private float billToPatient;
    private boolean cashUpFront;
    private float priceQuote;
    private String paymentMode;

    private String warnings;
    private String commentsToCrew;
    private String billingNotes;
    private String phoneFrom;
    private String phoneTo;
    private Integer status;

    private boolean activateThisRun;
    private Date activateThisRunTime;
    private boolean beAtOrigin;
    private Date beAtOriginTime;
    private boolean beAtOriginPrecise;
    private boolean beAtDestination;
    private Date beAtDestinationTime;
    private String tripType;
    private Date returnTripActivatedAt;
    private Date pickBackupAt;
    private String frequency;
    private boolean forever;
    private int noOfTimes;
    private Date through;

    private int shift;
    private String shiftInfo;

    private String disposition;
    private Date crewNotifiedTime;
    private Date acknowledgeTime;
    private Date arriveOnSceneTime;
    private Date patientContactTime;
    private Date transportBeginTime;
    private Date otherEMSArrivedTime;
    private Date atDestination;
    private Date transferSignatureTime;

    private String signatoryName;
    private String signatoryType;

    private Date backInServiceTime;
    private Date cancelledTime;
    private Date backAtStationTime;
    private Date completedTime;

    private String createdBy;
    private Date createdDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return Destination;
    }

    public void setDestination(int destination) {
        Destination = destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(int serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public boolean isBillable() {
        return isBillable;
    }

    public void setBillable(boolean billable) {
        isBillable = billable;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isPriorAuth() {
        return priorAuth;
    }

    public void setPriorAuth(boolean priorAuth) {
        this.priorAuth = priorAuth;
    }

    public float getBillToInsurance() {
        return billToInsurance;
    }

    public void setBillToInsurance(float billToInsurance) {
        this.billToInsurance = billToInsurance;
    }

    public float getBillToFacility() {
        return billToFacility;
    }

    public void setBillToFacility(float billToFacility) {
        this.billToFacility = billToFacility;
    }

    public float getBillToAffiliate() {
        return billToAffiliate;
    }

    public void setBillToAffiliate(float billToAffiliate) {
        this.billToAffiliate = billToAffiliate;
    }

    public float getBillToPatient() {
        return billToPatient;
    }

    public void setBillToPatient(float billToPatient) {
        this.billToPatient = billToPatient;
    }

    public boolean isCashUpFront() {
        return cashUpFront;
    }

    public void setCashUpFront(boolean cashUpFront) {
        this.cashUpFront = cashUpFront;
    }

    public float getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(float priceQuote) {
        this.priceQuote = priceQuote;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getCommentsToCrew() {
        return commentsToCrew;
    }

    public void setCommentsToCrew(String commentsToCrew) {
        this.commentsToCrew = commentsToCrew;
    }

    public String getBillingNotes() {
        return billingNotes;
    }

    public void setBillingNotes(String billingNotes) {
        this.billingNotes = billingNotes;
    }

    public String getPhoneFrom() {
        return phoneFrom;
    }

    public void setPhoneFrom(String phoneFrom) {
        this.phoneFrom = phoneFrom;
    }

    public String getPhoneTo() {
        return phoneTo;
    }

    public void setPhoneTo(String phoneTo) {
        this.phoneTo = phoneTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isActivateThisRun() {
        return activateThisRun;
    }

    public void setActivateThisRun(boolean activateThisRun) {
        this.activateThisRun = activateThisRun;
    }

    public Date getActivateThisRunTime() {
        return activateThisRunTime;
    }

    public void setActivateThisRunTime(Date activateThisRunTime) {
        this.activateThisRunTime = activateThisRunTime;
    }

    public boolean isBeAtOrigin() {
        return beAtOrigin;
    }

    public void setBeAtOrigin(boolean beAtOrigin) {
        this.beAtOrigin = beAtOrigin;
    }

    public Date getBeAtOriginTime() {
        return beAtOriginTime;
    }

    public void setBeAtOriginTime(Date beAtOriginTime) {
        this.beAtOriginTime = beAtOriginTime;
    }

    public boolean isBeAtOriginPrecise() {
        return beAtOriginPrecise;
    }

    public void setBeAtOriginPrecise(boolean beAtOriginPrecise) {
        this.beAtOriginPrecise = beAtOriginPrecise;
    }

    public boolean isBeAtDestination() {
        return beAtDestination;
    }

    public void setBeAtDestination(boolean beAtDestination) {
        this.beAtDestination = beAtDestination;
    }

    public Date getBeAtDestinationTime() {
        return beAtDestinationTime;
    }

    public void setBeAtDestinationTime(Date beAtDestinationTime) {
        this.beAtDestinationTime = beAtDestinationTime;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Date getReturnTripActivatedAt() {
        return returnTripActivatedAt;
    }

    public void setReturnTripActivatedAt(Date returnTripActivatedAt) {
        this.returnTripActivatedAt = returnTripActivatedAt;
    }

    public Date getPickBackupAt() {
        return pickBackupAt;
    }

    public void setPickBackupAt(Date pickBackupAt) {
        this.pickBackupAt = pickBackupAt;
    }

    public boolean isForever() {
        return forever;
    }

    public void setForever(boolean forever) {
        this.forever = forever;
    }

    public int getNoOfTimes() {
        return noOfTimes;
    }

    public void setNoOfTimes(int noOfTimes) {
        this.noOfTimes = noOfTimes;
    }

    public Date getThrough() {
        return through;
    }

    public void setThrough(Date through) {
        this.through = through;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public Date getCrewNotifiedTime() {
        return crewNotifiedTime;
    }

    public void setCrewNotifiedTime(Date crewNotifiedTime) {
        this.crewNotifiedTime = crewNotifiedTime;
    }

    public Date getAcknowledgeTime() {
        return acknowledgeTime;
    }

    public void setAcknowledgeTime(Date acknowledgeTime) {
        this.acknowledgeTime = acknowledgeTime;
    }

    public Date getArriveOnSceneTime() {
        return arriveOnSceneTime;
    }

    public void setArriveOnSceneTime(Date arriveOnSceneTime) {
        this.arriveOnSceneTime = arriveOnSceneTime;
    }

    public Date getPatientContactTime() {
        return patientContactTime;
    }

    public void setPatientContactTime(Date patientContactTime) {
        this.patientContactTime = patientContactTime;
    }

    public Date getTransportBeginTime() {
        return transportBeginTime;
    }

    public void setTransportBeginTime(Date transportBeginTime) {
        this.transportBeginTime = transportBeginTime;
    }

    public Date getOtherEMSArrivedTime() {
        return otherEMSArrivedTime;
    }

    public void setOtherEMSArrivedTime(Date otherEMSArrivedTime) {
        this.otherEMSArrivedTime = otherEMSArrivedTime;
    }

    public Date getAtDestination() {
        return atDestination;
    }

    public void setAtDestination(Date atDestination) {
        this.atDestination = atDestination;
    }

    public Date getTransferSignatureTime() {
        return transferSignatureTime;
    }

    public void setTransferSignatureTime(Date transferSignatureTime) {
        this.transferSignatureTime = transferSignatureTime;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getSignatoryType() {
        return signatoryType;
    }

    public void setSignatoryType(String signatoryType) {
        this.signatoryType = signatoryType;
    }

    public Date getBackInServiceTime() {
        return backInServiceTime;
    }

    public void setBackInServiceTime(Date backInServiceTime) {
        this.backInServiceTime = backInServiceTime;
    }

    public Date getCancelledTime() {
        return cancelledTime;
    }

    public void setCancelledTime(Date cancelledTime) {
        this.cancelledTime = cancelledTime;
    }

    public Date getBackAtStationTime() {
        return backAtStationTime;
    }

    public void setBackAtStationTime(Date backAtStationTime) {
        this.backAtStationTime = backAtStationTime;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getServiceLevelName() {
        return serviceLevelName;
    }

    public void setServiceLevelName(String serviceLevelName) {
        this.serviceLevelName = serviceLevelName;
    }

    public String getShiftInfo() {
        return shiftInfo;
    }

    public void setShiftInfo(String shiftInfo) {
        this.shiftInfo = shiftInfo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
