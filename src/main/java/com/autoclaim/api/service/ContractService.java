package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;

public interface ContractService {
	ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract);
	ContractDetailsResponseModel updateContract(String contractNo, ContractDetailsRequestModel contract);
	ContractDetailsResponseModel getContract(String contractNo);
	ContractDetailsResponseModel deleteContract(String contractNo);
	ArrayList<ContractDetailsResponseModel> getAllContracts();
	ArrayList<ContractDetailsResponseModel> getSomeContracts(int page, int limit);
	ArrayList<ContractDetailsResponseModel> deleteMultipleContracts(ArrayList<String> contracts);
	ArrayList<String> getALlContractNo(String id);
}
