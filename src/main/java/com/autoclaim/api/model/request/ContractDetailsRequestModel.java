package com.autoclaim.api.model.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class ContractDetailsRequestModel {

	@NotNull
	@Length(min=3, max=25)
	private String contractNo;

	@NotNull
    private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@NotNull
	@Length(min=3, max=25)
	private String insuredName;
	
	@NotNull
	@Length(min=3, max=16)
	private String VehicleRegistrationNo;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
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

	public String getVehicleRegistrationNo() {
		return VehicleRegistrationNo;
	}

	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		VehicleRegistrationNo = vehicleRegistrationNo;
	}

}
