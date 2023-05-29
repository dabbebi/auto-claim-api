package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.Contract;
import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	ContractRepository contractRepository;

	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract) {
		
		Contract contractEntity = new Contract();
		BeanUtils.copyProperties(contract, contractEntity);
		
		Contract createdContract = contractRepository.save(contractEntity);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(createdContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel updateContract(String contractNo, ContractDetailsRequestModel contract) {
		
		Contract storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		BeanUtils.copyProperties(contract, storedContract);
		contractRepository.save(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel getContract(String contractNo) {
		Contract storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel deleteContract(String contractNo) {
		Contract storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		contractRepository.delete(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ArrayList<ContractDetailsResponseModel> getAllContracts() {

		ArrayList<Contract> allContracts = (ArrayList<Contract>) contractRepository.findAll();
		
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		
		for(Contract contract : allContracts) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(contract, tempContract);
			returnValue.add(tempContract);
		}
		
		return returnValue;
	}

	@Override
	public ArrayList<ContractDetailsResponseModel> getSomeContracts(int page, int limit) {

		Pageable pageable = PageRequest.of(page, limit);
		Page<Contract> contractsPage = contractRepository.findAll(pageable);
		List<Contract> allContracts = contractsPage.getContent();

		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<>();

		for(Contract contract : allContracts) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(contract, tempContract);
			returnValue.add(tempContract);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ContractDetailsResponseModel> deleteMultipleContracts(ArrayList<String> contracts) {
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		for(String contract: contracts) {
			Contract storedContract = contractRepository.findContractByContractNo(contract);
			if(storedContract == null) throw new RuntimeException("Contract with number " + contract + " not found!");

			contractRepository.delete(storedContract);

			ContractDetailsResponseModel currentContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(storedContract, currentContract);
			returnValue.add(currentContract);
		}
		return returnValue;
	}

	@Override
	public ArrayList<String> getALlContractNo(String id) {
		ArrayList<String> returnValue = contractRepository.getAllContractNo(id);

		return returnValue;
	}

}
