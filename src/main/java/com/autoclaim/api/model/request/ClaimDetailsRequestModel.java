package com.autoclaim.api.model.request;

import java.util.ArrayList;
import java.util.Date;

public class ClaimDetailsRequestModel {
	
	private Date accidentDate;
	private String contractId;
    private ArrayList<String> pictures;
    
	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
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
