package com.autoclaim.api.model.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserPwdUpdateRequestModel {
	
	@NotNull
	@Length(min=3, max=25)
	private String oldPassword;
	
	@NotNull
	@Length(min=3, max=25)
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
