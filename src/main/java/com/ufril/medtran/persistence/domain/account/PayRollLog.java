package com.ufril.medtran.persistence.domain.account;

import javax.persistence.*;

@Entity
@Table(name = "payroll_log")
public class PayRollLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String year;
    private String month;
    private float regularHours;
    private float regularEarnings;
    private float overTime;
    private float overTimeEarnings;
    private float onCallHours;
    private float onCallEarnings;
    private float totalEarnings;
    private int companyId;

    @OneToOne
    @JoinColumn(name = "employee_ID", referencedColumnName = "id")
    private Employees employeeID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(float regularHours) {
        this.regularHours = regularHours;
    }

    public float getRegularEarnings() {
        return regularEarnings;
    }

    public void setRegularEarnings(float regularEarnings) {
        this.regularEarnings = regularEarnings;
    }

    public float getOverTime() {
        return overTime;
    }

    public void setOverTime(float overTime) {
        this.overTime = overTime;
    }

    public float getOverTimeEarnings() {
        return overTimeEarnings;
    }

    public void setOverTimeEarnings(float overTimeEarnings) {
        this.overTimeEarnings = overTimeEarnings;
    }

    public float getOnCallHours() {
        return onCallHours;
    }

    public void setOnCallHours(float onCallHours) {
        this.onCallHours = onCallHours;
    }

    public float getOnCallEarnings() {
        return onCallEarnings;
    }

    public void setOnCallEarnings(float onCallEarnings) {
        this.onCallEarnings = onCallEarnings;
    }

    public float getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(float totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Employees getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employees employeeID) {
        this.employeeID = employeeID;
    }
}
