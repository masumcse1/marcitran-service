package com.ufril.medtran.persistence.domain.dispatch;

import com.ufril.medtran.persistence.domain.common.Tag;
import com.ufril.medtran.persistence.domain.patient.Patients;

import javax.persistence.*;
import java.util.Date;

//@Entity

@Entity
@Table(name = "dispatches")
public class Dispatches {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String caller;
	private String phone;
	@ManyToOne
	private Tag tag;
	@ManyToOne
	private Facilities origin;
	@ManyToOne
	private Facilities Destination;
	private String reason;
	@ManyToOne
	private Patients patient;
	@ManyToOne
	private ServiceLevel serviceLevel;
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
	private int status;
	private String createdBy;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
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

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Facilities getOrigin() {
		return origin;
	}

	public void setOrigin(Facilities origin) {
		this.origin = origin;
	}

	public Facilities getDestination() {
		return Destination;
	}

	public void setDestination(Facilities destination) {
		Destination = destination;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	public ServiceLevel getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(ServiceLevel serviceLevel) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
