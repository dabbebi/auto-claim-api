package com.autoclaim.api.model.response;

import java.util.ArrayList;
import java.util.Date;
import com.autoclaim.api.enums.ClaimStatus;

public class ClaimDetailsResponseModel {

	private String claimNo;
	private Date accidentDate;
	private Date creationDate;
	private ClaimStatus status;
	private String contractNo;
    private ArrayList<String> pictures;
    
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public ClaimStatus getStatus() {
		return status;
	}
	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public ArrayList<String> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}
    
    
}
