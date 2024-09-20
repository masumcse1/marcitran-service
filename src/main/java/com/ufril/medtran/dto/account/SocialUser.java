package com.ufril.medtran.dto.account;

import com.ufril.medtran.enumeration.SocialType;
import com.ufril.medtran.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

public class SocialUser {

	@NotEmpty
	private String username;

	private String id;

	private String link;


	@ValidEmail
	@NotEmpty
	private String email;

	private String phone;

	@NotEmpty
	private String family_name;

	@NotEmpty
	private String given_name;
	private String gender;
	private String streetAddress;
	private String city;
	private String picture;
	private SocialType socialType;

	@NotEmpty
	private String accessToken;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	


	public String getFamily_name() {
		return family_name;
	}


	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}


	public String getGiven_name() {
		return given_name;
	}


	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
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


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public SocialType getSocialType() {
		return socialType;
	}


	public void setSocialType(SocialType signInProvider) {
		this.socialType = signInProvider;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CreateUserDTO{");
		sb.append("username='").append(username).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", firstName='").append(family_name).append('\'');
		sb.append(", lastName='").append(given_name).append('\'');
		sb.append(", gender=").append(gender);
		sb.append(", streetAddress='").append(streetAddress).append('\'');
		sb.append(", city='").append(city).append('\'');
		sb.append(", profilePicture='").append(picture).append('\'');
		sb.append(", signInProvider=").append(socialType);
		sb.append('}');
		return sb.toString();
	}
}
