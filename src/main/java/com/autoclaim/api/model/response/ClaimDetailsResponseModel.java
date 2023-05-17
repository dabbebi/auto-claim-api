package com.autoclaim.api.model.response;

import java.util.ArrayList;
import java.util.Date;
import com.autoclaim.api.enums.ClaimStatus;

public class ClaimDetailsResponseModel {

	private String publicId;
	private Date accidentDate;
	private Date creationDate;
	private ClaimStatus status;
	private String contractId;
    private ArrayList<String> pictures;
    
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
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
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public ArrayList<String> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<String> pictures) {
		this.pictures = pictures;
	}
    
    
}
