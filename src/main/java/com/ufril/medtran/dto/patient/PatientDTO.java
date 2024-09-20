package com.ufril.medtran.dto.patient;

import com.ufril.medtran.enumeration.GenderType;

import java.util.Date;

public class PatientDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;
	private Integer status;
	private String warnings;
	private String barrierEMS;
	private boolean residenceFacility;
	private String room;
	private String socialSecurity;
	private String mbi;
	private String barcode;
	private Date dateOfBirth;
	private int age;
	private GenderType gender;
	private String ethnicity;
	private String homePhone;
	private String mobilePhone;
	private String workPhone;
	private String email;
	private String mailingStreetAddress;
	private String floorRoomFacility;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String censusTract;

	private String otherData;

	private String pastMedicalHistory;
	private String avpu;
	private String chiefCompliant;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getBarrierEMS() {
		return barrierEMS;
	}

	public void setBarrierEMS(String barrierEMS) {
		this.barrierEMS = barrierEMS;
	}

	public boolean isResidenceFacility() {
		return residenceFacility;
	}

	public void setResidenceFacility(boolean residenceFacility) {
		this.residenceFacility = residenceFacility;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getMbi() {
		return mbi;
	}

	public void setMbi(String mbi) {
		this.mbi = mbi;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailingStreetAddress() {
		return mailingStreetAddress;
	}

	public void setMailingStreetAddress(String mailingStreetAddress) {
		this.mailingStreetAddress = mailingStreetAddress;
	}

	public String getFloorRoomFacility() {
		return floorRoomFacility;
	}

	public void setFloorRoomFacility(String floorRoomFacility) {
		this.floorRoomFacility = floorRoomFacility;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCensusTract() {
		return censusTract;
	}

	public void setCensusTract(String censusTract) {
		this.censusTract = censusTract;
	}

	public String getOtherData() {
		return otherData;
	}

	public void setOtherData(String otherData) {
		this.otherData = otherData;
	}

	public String getPastMedicalHistory() {
		return pastMedicalHistory;
	}

	public void setPastMedicalHistory(String pastMedicalHistory) {
		this.pastMedicalHistory = pastMedicalHistory;
	}

	public String getAvpu() {
		return avpu;
	}

	public void setAvpu(String avpu) {
		this.avpu = avpu;
	}

	public String getChiefCompliant() {
		return chiefCompliant;
	}

	public void setChiefCompliant(String chiefCompliant) {
		this.chiefCompliant = chiefCompliant;
	}
}
