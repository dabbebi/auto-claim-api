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

import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	ContractRepository contractRepository;

	private String generateNextContractNumber(Date starDate) {

		int currentYear = starDate.getYear();
		int max = contractRepository.getMaxId();
		String currentYearStr = String.valueOf(currentYear);
		if(currentYearStr.length() > 2) {
			currentYearStr = currentYearStr.substring(currentYearStr.length() - 2);
		}

		String contractNo = "CN-" + Long.toString(max + 1) + "-" + currentYearStr;
		return contractNo;
	}

	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract) {
		
		ContractEntity contractEntity = new ContractEntity();
		BeanUtils.copyProperties(contract, contractEntity);

		contractEntity.setContractNo(generateNextContractNumber(contractEntity.getStartDate()));
		
		ContractEntity createdContract = contractRepository.save(contractEntity);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(createdContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel updateContract(String contractNo, ContractDetailsRequestModel contract) {
		
		ContractEntity storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		BeanUtils.copyProperties(contract, storedContract);
		contractRepository.save(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel getContract(String contractNo) {
		ContractEntity storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel deleteContract(String contractNo) {
		ContractEntity storedContract = contractRepository.findContractByContractNo(contractNo);
		if(storedContract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");
		
		contractRepository.delete(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ArrayList<ContractDetailsResponseModel> getAllContracts() {

		ArrayList<ContractEntity> allContracts = (ArrayList<ContractEntity>) contractRepository.findAll();
		
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		
		for(ContractEntity contractEntity: allContracts) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(contractEntity, tempContract);
			returnValue.add(tempContract);
		}
		
		return returnValue;
	}

	@Override
	public ArrayList<ContractDetailsResponseModel> getSomeContracts(int page, int limit) {

		Pageable pageable = PageRequest.of(page, limit);
		Page<ContractEntity> contractsPage = contractRepository.findAll(pageable);
		List<ContractEntity> allContracts = contractsPage.getContent();

		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<>();

		for(ContractEntity contractEntity: allContracts) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(contractEntity, tempContract);
			returnValue.add(tempContract);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ContractDetailsResponseModel> deleteMultipleContracts(ArrayList<String> contracts) {
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		for(String contract: contracts) {
			ContractEntity storedContract = contractRepository.findContractByContractNo(contract);
			if(storedContract == null) throw new RuntimeException("Contract with number " + contract + " not found!");

			contractRepository.delete(storedContract);

			ContractDetailsResponseModel currentContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(storedContract, currentContract);
			returnValue.add(currentContract);
		}
		return returnValue;
	}

}
