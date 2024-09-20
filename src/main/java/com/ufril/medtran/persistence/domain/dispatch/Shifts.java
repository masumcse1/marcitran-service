package com.ufril.medtran.persistence.domain.dispatch;

import com.ufril.medtran.persistence.domain.account.EmployeeCertificates;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "shifts")
public class Shifts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String basedFromLocation;
	private String postingLocation;
	private String primaryRole;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	private float expectedLength;

	private String primaryCheckList;
	private String secondaryCheckList;
	@ManyToOne
	private Vehicles vehicle;
	@ManyToOne
	private ServiceLevel effServiceLevel;
	private float startingOdometer;
	private float endOdometer;
	private Integer status;
	private String fuelLevel;
	private String shiftType;

	@OneToMany(mappedBy = "shiftID", fetch = FetchType.LAZY)
	private Collection<ShiftCrewMembers> shiftCrewMembers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBasedFromLocation() {
		return basedFromLocation;
	}

	public void setBasedFromLocation(String basedFromLocation) {
		this.basedFromLocation = basedFromLocation;
	}

	public String getPostingLocation() {
		return postingLocation;
	}

	public void setPostingLocation(String postingLocation) {
		this.postingLocation = postingLocation;
	}

	public String getPrimaryRole() {
		return primaryRole;
	}

	public void setPrimaryRole(String primaryRole) {
		this.primaryRole = primaryRole;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public float getExpectedLength() {
		return expectedLength;
	}

	public void setExpectedLength(float expectedLength) {
		this.expectedLength = expectedLength;
	}

	public String getPrimaryCheckList() {
		return primaryCheckList;
	}

	public void setPrimaryCheckList(String primaryCheckList) {
		this.primaryCheckList = primaryCheckList;
	}

	public String getSecondaryCheckList() {
		return secondaryCheckList;
	}

	public void setSecondaryCheckList(String secondaryCheckList) {
		this.secondaryCheckList = secondaryCheckList;
	}

	public Vehicles getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	public ServiceLevel getEffServiceLevel() {
		return effServiceLevel;
	}

	public void setEffServiceLevel(ServiceLevel effServiceLevel) {
		this.effServiceLevel = effServiceLevel;
	}

	public float getStartingOdometer() {
		return startingOdometer;
	}

	public void setStartingOdometer(float startingOdometer) {
		this.startingOdometer = startingOdometer;
	}

	public float getEndOdometer() {
		return endOdometer;
	}

	public void setEndOdometer(float endOdometer) {
		this.endOdometer = endOdometer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(String fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public Collection<ShiftCrewMembers> getShiftCrewMembers() {
		return shiftCrewMembers;
	}

	public void setShiftCrewMembers(Collection<ShiftCrewMembers> shiftCrewMembers) {
		this.shiftCrewMembers = shiftCrewMembers;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
}

