package com.autoclaim.api.model.request;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.autoclaim.api.enums.ClaimStatus;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
public class ClaimDetailsRequestModel {

	@NotNull
	@Length(min=3, max=25)
	private String claimNo;

	@NotNull
	private Date accidentDate;
	
	@NotNull
	private String contractNo;

	@NotNull
	private ClaimStatus status;

    private ArrayList<PictureDetailsRequestModel> pictures;

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public ArrayList<PictureDetailsRequestModel> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<PictureDetailsRequestModel> pictures) {
		this.pictures = pictures;
	}

	public ClaimStatus getStatus() {
		return this.status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
}
