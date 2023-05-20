package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ContractService;
import com.autoclaim.api.shared.Utils;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	Utils utils;

	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract) {
		
		ContractEntity contractEntity = new ContractEntity();
		BeanUtils.copyProperties(contract, contractEntity);
		
		contractEntity.setPublicId(utils.generateRandomString(30));
		
		ContractEntity createdContract = contractRepository.save(contractEntity);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(createdContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel updateContract(String publicId, ContractDetailsRequestModel contract) {
		
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " +publicId + " not found!");
		
		BeanUtils.copyProperties(contract, storedContract);
		contractRepository.save(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel getContract(String publicId) {
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " + publicId + " not found!");
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ContractDetailsResponseModel deleteContract(String publicId) {
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " +publicId + " not found!");
		
		contractRepository.delete(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		
		return returnValue;
	}

	public ArrayList<ContractDetailsResponseModel> getAllContract() {
		
		ArrayList<ContractEntity> allContracts = (ArrayList<ContractEntity>) contractRepository.findAll();
		
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		
		for(int i = 0; i < allContracts.size(); i++) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(allContracts.get(i), tempContract);
			returnValue.add(tempContract);
		}
		
		return returnValue;
	}

}
