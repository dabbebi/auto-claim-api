package com.autoclaim.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserDetailsRequestModel {
	
	@NotNull
	@Length(min=3, max=25)
	private String firstName;
	
	@NotNull
	@Length(min=3, max=25)
	private String lastName;
	
	@Length(min=8, max=8)
	private String cin;
	
	@Email
	@Length(max=64)
	private String email;
	
	@NotNull
	@Length(min=3, max=25)
	private String password;
	
	@Length(min=8, max=8)
	private String telephone;
	
	private String address;
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
