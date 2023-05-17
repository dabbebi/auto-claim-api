package com.autoclaim.api.model.response;

import java.util.Date;

public class ContractDetailsResponseModel {

	private String publicId;
    private Date startDate;
	private Date endDate;
	private String userId;
	private String VehicleRegistrationNo;
	
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVehicleRegistrationNo() {
		return VehicleRegistrationNo;
	}
	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		VehicleRegistrationNo = vehicleRegistrationNo;
	}
	
	
}
