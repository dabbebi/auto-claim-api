package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;

public interface ContractService {
	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract);
	public ContractDetailsResponseModel updateContract(String contractNo, ContractDetailsRequestModel contract);
	public ContractDetailsResponseModel getContract(String contractNo);
	public ContractDetailsResponseModel deleteContract(String contractNo);
	public ArrayList<ContractDetailsResponseModel> getAllContracts();
	public ArrayList<ContractDetailsResponseModel> getSomeContracts(int page, int limit);
	public ArrayList<ContractDetailsResponseModel> deleteMultipleContracts(ArrayList<String> contracts);
}
