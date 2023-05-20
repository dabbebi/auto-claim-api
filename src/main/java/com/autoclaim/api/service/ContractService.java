package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;

public interface ContractService {
	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract);
	public ContractDetailsResponseModel updateContract(String publicId, ContractDetailsRequestModel contract);
	public ContractDetailsResponseModel getContract(String publicId);
	public ContractDetailsResponseModel deleteContract(String publicId);
	public ArrayList<ContractDetailsResponseModel> getAllContract();
}
