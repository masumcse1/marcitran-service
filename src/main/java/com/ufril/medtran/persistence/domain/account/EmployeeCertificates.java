package com.ufril.medtran.persistence.domain.account;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_certificates")
public class EmployeeCertificates {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Certificates certificateID;
	@ManyToOne
	private Employees employeeID;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Certificates getCertificateID() {
		return certificateID;
	}

	public void setCertificateID(Certificates certificateID) {
		this.certificateID = certificateID;
	}

	public Employees getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employees employeeID) {
		this.employeeID = employeeID;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}
}
