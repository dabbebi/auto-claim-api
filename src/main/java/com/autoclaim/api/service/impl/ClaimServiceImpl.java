package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.ClaimEntity;
import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.enums.ClaimStatus;
import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;
import com.autoclaim.api.repository.ClaimRepository;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {
	
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	ContractRepository contractRepository;

	private String generateNextClaimNumber() {

		int max = claimRepository.getMaxId();
		String currentYearStr = String.valueOf((new Date()).getYear());
		if(currentYearStr.length() > 2) {
			currentYearStr = currentYearStr.substring(currentYearStr.length() - 2);
		}

		String claimNo = "CL-" + Long.toString(max + 1) + "-" + currentYearStr;
		return claimNo;
	}
	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel claim) {
		ContractEntity contract = contractRepository.findContractByContractNo(claim.getContractNo());
		if(contract == null) throw new RuntimeException("Contract with number " + claim.getContractNo() + " not found!");
		
		ClaimEntity claimEntity = new ClaimEntity();
		BeanUtils.copyProperties(claim, claimEntity);

		claimEntity.setCreationDate(new Date());
		claimEntity.setContract(contract);
		claimEntity.setStatus(ClaimStatus.OPEN);
		claimEntity.setClaimNo(generateNextClaimNumber());

		ClaimEntity createdClaim = claimRepository.save(claimEntity);
		
		Set<ClaimEntity> claims = contract.getClaims();
		if(claims == null)
			claims = new HashSet<ClaimEntity>();
		claims.add(createdClaim);
		contractRepository.save(contract);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(createdClaim, returnValue);
		returnValue.setContractNo(createdClaim.getContract().getContractNo());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel updateClaim(String claimNo, ClaimDetailsRequestModel claim) {
		ClaimEntity storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " + claimNo + " not found!");
		
		BeanUtils.copyProperties(claim, storedClaim);
		claimRepository.save(storedClaim);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractNo(storedClaim.getContract().getContractNo());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel getClaim(String claimNo) {
		ClaimEntity storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " + claimNo + " not found!");
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractNo(storedClaim.getContract().getContractNo());
		
		return returnValue;
	}

	public ClaimDetailsResponseModel deleteClaim(String claimNo) {
		ClaimEntity storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with public id " + claimNo + " not found!");
		
		claimRepository.delete(storedClaim);
		
		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);
		returnValue.setContractNo(storedClaim.getContract().getContractNo());
		
		return returnValue;
	}

	public ArrayList<ClaimDetailsResponseModel> getContractClaims(String contractNo) {
		ContractEntity contract = contractRepository.findContractByContractNo(contractNo);
		if(contract == null) throw new RuntimeException("Contract with public id " + contractNo + " not found!");
		
		ArrayList<ClaimEntity> allClaims = claimRepository.findClaimByContract(contract);
		
		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<ClaimDetailsResponseModel>();
		
		for(ClaimEntity claimEntity: allClaims) {
			ClaimDetailsResponseModel tempClaim = new ClaimDetailsResponseModel();
			BeanUtils.copyProperties(claimEntity, tempClaim);
			tempClaim.setContractNo(contractNo);
			returnValue.add(tempClaim);
		}
		
		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> getAllClaims() {

		ArrayList<ClaimEntity> allClaims = (ArrayList<ClaimEntity>) claimRepository.findAll();

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<ClaimDetailsResponseModel>();

		for(ClaimEntity claimEntity: allClaims) {
			ClaimDetailsResponseModel tempClaim = new ClaimDetailsResponseModel();
			BeanUtils.copyProperties(claimEntity, tempClaim);
			tempClaim.setContractNo(claimEntity.getContract().getContractNo());
			returnValue.add(tempClaim);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ClaimEntity> claimEntityPage = claimRepository.findAll(pageableRequest);

		ArrayList<ClaimEntity> allClaims = (ArrayList<ClaimEntity>) claimEntityPage.getContent();

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();

		for(ClaimEntity claimEntity: allClaims) {
			ClaimDetailsResponseModel tempClaim = new ClaimDetailsResponseModel();
			BeanUtils.copyProperties(claimEntity, tempClaim);
			tempClaim.setContractNo(claimEntity.getContract().getContractNo());
			returnValue.add(tempClaim);
		}

		return returnValue;
	}

}