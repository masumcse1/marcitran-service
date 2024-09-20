package com.ufril.medtran.persistence.domain.dispatch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dispatchSchedules")
public class DispatchSchedules {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "dispatchId", referencedColumnName = "id", nullable = false)
	private Dispatches dispatch;

	private boolean activateThisRun;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date activateThisRunTime;
	private boolean beAtOrigin;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date beAtOriginTime;
	private boolean beAtOriginPrecise;
	private boolean beAtDestination;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date beAtDestinationTime;
	private String tripType;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnTripActivatedAt;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date pickBackupAt;

	private String frequency;

	private boolean forever;
	private int noOfTimes;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date through;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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

	public Dispatches getDispatch() {
		return dispatch;
	}

	public void setDispatch(Dispatches dispatch) {
		this.dispatch = dispatch;
	}
}
