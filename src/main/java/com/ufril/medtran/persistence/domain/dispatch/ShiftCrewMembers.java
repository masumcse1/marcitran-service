package com.ufril.medtran.persistence.domain.dispatch;

import com.ufril.medtran.persistence.domain.account.Employees;

import javax.persistence.*;

@Entity
@Table(name = "shift_crew_members")
public class ShiftCrewMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Shifts shiftID;

    @ManyToOne
    private Employees employeeID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shifts getShiftID() {
        return shiftID;
    }

    public void setShiftID(Shifts shiftID) {
        this.shiftID = shiftID;
    }

    public Employees getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employees employeeID) {
        this.employeeID = employeeID;
    }
}
