package com.ufril.medtran.persistence.domain.account;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userloginaudits")
public class UserLoginAudits {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String ipAddress;
	private String userAgent;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	private String status;
	@ManyToOne
	private User usernameFK;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUsernameFK() {
		return usernameFK;
	}

	public void setUsernameFK(User usernameFK) {
		this.usernameFK = usernameFK;
	}
}
