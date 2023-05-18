package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.ClaimEntity;
import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;
import com.autoclaim.api.repository.ClaimRepository;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ClaimService;
import com.autoclaim.api.shared.Utils;

@Service
public class ClaimServiceImpl implements ClaimService {
	
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	Utils utils;

	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel claim) {
		ContractEntity contract = contractRepository.findContractByPublicId(claim.getContractId());
		if(contract == null) throw new RuntimeException("User with public id " + claim.getContractId() + " not found!");
		
		ClaimEntity claimEntity = new ClaimEntity();
		BeanUtils.copyProperties(contract, claimEntity);
		claimEntity.setCreationDate(new Date());
		claimEntity.setContract(contract);
		
		claimEntity.setPublicId(utils.generateRandomString(30));
		
		ClaimEntity createdClaim = claimRepository.save(claimEntity);
		
		Set<ClaimEntity> claims = contract.getClaims();
		if(claims == null)
			claims = new HashSet<ClaimEntity>();
		claims.add(createdClaim);
		contractRepository.save(contract);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(createdClaim, returnValue);
		returnValue.setContractId(createdClaim.getContract().getPublicId());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel updateClaim(String publicId, ClaimDetailsRequestModel claim) {
		ClaimEntity storedClaim = claimRepository.findClaimByPublicId(publicId);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " +publicId + " not found!");
		
		BeanUtils.copyProperties(claim, storedClaim);
		claimRepository.save(storedClaim);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractId(storedClaim.getContract().getPublicId());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel getClaim(String publicId) {
		ClaimEntity storedClaim = claimRepository.findClaimByPublicId(publicId);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " + publicId + " not found!");
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractId(storedClaim.getContract().getPublicId());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel deleteClaim(String publicId) {
		ClaimEntity storedClaim = claimRepository.findClaimByPublicId(publicId);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " +publicId + " not found!");
		
		claimRepository.delete(storedClaim);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractId(storedClaim.getContract().getPublicId());
		
		return returnValue;
	}

	public ArrayList<ClaimDetailsResponseModel> getContractClaims(String contractId) {
		ContractEntity contract = contractRepository.findContractByPublicId(contractId);
		if(contract == null) throw new RuntimeException("Contract with public id " + contractId + " not found!");
		
		ArrayList<ClaimEntity> allClaims = claimRepository.findClaimByContract(contract);
		
		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<ClaimDetailsResponseModel>();
		
		for(int i = 0; i < allClaims.size(); i++) {
			ClaimDetailsResponseModel tempClaim = new ClaimDetailsResponseModel();
			BeanUtils.copyProperties(allClaims.get(i), tempClaim);
			tempClaim.setContractId(contractId);
			returnValue.add(tempClaim);
		}
		
		return returnValue;
	}

}
