package com.autoclaim.api.service.impl;

import java.util.ArrayList;
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

}
