package com.ufril.medtran.persistence.domain.account;

import com.ufril.medtran.enumeration.GenderType;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullName;
    @Column(length = 50)
    private String phone;
    private String assignedStation;
    private String emergencyContactNo;
    private String getEmergencyContactName;
    private String employeeType;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstHired;
    private String payType;
    private boolean useTimeClock;
    @ColumnDefault("'0.0'")
    private float hourlyPay;
    private String keysAndCodes;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String citizenship;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String ethnicity;
    private boolean active;
    private int companyId;

    @OneToOne
    @JoinColumn(name = "username_fk", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "employeeID", fetch = FetchType.LAZY)
    private List<EmployeeCertificates> employeeCertificates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAssignedStation() {
        return assignedStation;
    }

    public void setAssignedStation(String assignedStation) {
        this.assignedStation = assignedStation;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getGetEmergencyContactName() {
        return getEmergencyContactName;
    }

    public void setGetEmergencyContactName(String getEmergencyContactName) {
        this.getEmergencyContactName = getEmergencyContactName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Date getFirstHired() {
        return firstHired;
    }

    public void setFirstHired(Date firstHired) {
        this.firstHired = firstHired;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public boolean isUseTimeClock() {
        return useTimeClock;
    }

    public void setUseTimeClock(boolean useTimeClock) {
        this.useTimeClock = useTimeClock;
    }

    public float getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(float hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public String getKeysAndCodes() {
        return keysAndCodes;
    }

    public void setKeysAndCodes(String keysAndCodes) {
        this.keysAndCodes = keysAndCodes;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EmployeeCertificates> getEmployeeCertificates() {
        return employeeCertificates;
    }

    public void setEmployeeCertificates(List<EmployeeCertificates> employeeCertificates) {
        this.employeeCertificates = employeeCertificates;
    }
}
