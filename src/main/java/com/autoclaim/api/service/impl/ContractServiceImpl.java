package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.entity.UserEntity;
import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.repository.UserRepository;
import com.autoclaim.api.service.ContractService;
import com.autoclaim.api.shared.Utils;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	public ContractDetailsResponseModel createContract(ContractDetailsRequestModel contract) {

		UserEntity user= userRepository.findUserByPublicId(contract.getUserId());
		if(user == null) throw new RuntimeException("User with public id " + contract.getUserId() + " not found!");
		
		ContractEntity contractEntity = new ContractEntity();
		BeanUtils.copyProperties(contract, contractEntity);
		contractEntity.setUser(user);
		
		contractEntity.setPublicId(utils.generateRandomString(30));
		
		ContractEntity createdContract = contractRepository.save(contractEntity);
		
		Set<ContractEntity> contracts = user.getContracts();
		if(contracts == null)
			contracts = new HashSet<ContractEntity>();
		contracts.add(createdContract);
		userRepository.save(user);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(createdContract, returnValue);
		returnValue.setUserId(createdContract.getUser().getPublicId());
		
		return returnValue;
	}

	public ContractDetailsResponseModel updateContract(String publicId, ContractDetailsRequestModel contract) {
		
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " +publicId + " not found!");
		
		BeanUtils.copyProperties(contract, storedContract);
		contractRepository.save(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		returnValue.setUserId(storedContract.getUser().getPublicId());
		
		return returnValue;
	}

	public ContractDetailsResponseModel getContract(String publicId) {
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " + publicId + " not found!");
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		returnValue.setUserId(storedContract.getUser().getPublicId());
		
		return returnValue;
	}

	public ContractDetailsResponseModel deleteContract(String publicId) {
		ContractEntity storedContract = contractRepository.findContractByPublicId(publicId);
		if(storedContract == null) throw new RuntimeException("Contract with public id " +publicId + " not found!");
		
		contractRepository.delete(storedContract);
		
		ContractDetailsResponseModel returnValue = new ContractDetailsResponseModel();
		BeanUtils.copyProperties(storedContract, returnValue);
		returnValue.setUserId(storedContract.getUser().getPublicId());
		
		return returnValue;
	}

	public ArrayList<ContractDetailsResponseModel> getAllContract(String userId) {
		
		UserEntity user= userRepository.findUserByPublicId(userId);
		if(user == null) throw new RuntimeException("User with public id " + userId + " not found!");
		
		ArrayList<ContractEntity> allContracts = contractRepository.findContractByUser(user);
		
		ArrayList<ContractDetailsResponseModel> returnValue = new ArrayList<ContractDetailsResponseModel>();
		
		for(int i = 0; i < allContracts.size(); i++) {
			ContractDetailsResponseModel tempContract = new ContractDetailsResponseModel();
			BeanUtils.copyProperties(allContracts.get(i), tempContract);
			tempContract.setUserId(userId);
			returnValue.add(tempContract);
		}
		
		return returnValue;
	}

}
