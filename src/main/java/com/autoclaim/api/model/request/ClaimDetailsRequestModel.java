package com.autoclaim.api.model.request;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.autoclaim.api.enums.ClaimStatus;
import org.springframework.validation.annotation.Validated;

@Validated
public class ClaimDetailsRequestModel {
	
	@NotNull
	private Date accidentDate;
	
	@NotNull
	private String contractNo;

	@NotNull
	private ClaimStatus status;

    private ArrayList<PictureDetailsRequestModel> pictures;
    
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
