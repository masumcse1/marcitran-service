package com.ufril.medtran.dto.account;

import com.ufril.medtran.validation.ValidChars;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author moin
 */
public class UpdateProfileDTO {

	@NotEmpty
	private String email;
    @NotEmpty
    @ValidChars
    @Size(min = 2, max = 50)
	private String username;
	private boolean twoFAEnabled;
	private boolean locked;
	private String status;

//    private String lastName;
//    private GenderType gender;
//    private String apartment;
//    private String house;
//    private String streetAddress;
//    private String city;
//    private String state;
//    private String zipCode;
//    private String country;


	public boolean isTwoFAEnabled() {
		return twoFAEnabled;
	}

	public void setTwoFAEnabled(boolean twoFAEnabled) {
		this.twoFAEnabled = twoFAEnabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
