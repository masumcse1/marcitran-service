package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dispatchlogs")
public class DispatchLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "dispatchId", referencedColumnName = "id", nullable = false)
	private Dispatches dispatch;
	@ManyToOne
	private Shifts shift;
	private String disposition;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date crewNotifiedTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date acknowledgeTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date arriveOnSceneTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date patientContactTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date transportBeginTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date otherEMSArrivedTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date atDestination;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date transferSignatureTime;

	private String signatoryName;
	private String signatoryType;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date backInServiceTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date cancelledTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date backAtStationTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedTime;

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

	public Shifts getShift() {
		return shift;
	}

	public void setShift(Shifts shift) {
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
}
