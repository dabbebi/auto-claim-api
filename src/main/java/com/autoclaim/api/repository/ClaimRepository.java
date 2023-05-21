package com.autoclaim.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ClaimEntity;
import com.autoclaim.api.entity.ContractEntity;

@Repository
public interface ClaimRepository extends PagingAndSortingRepository<ClaimEntity, Long> {
	public ClaimEntity findClaimByPublicId(String publicId);
	public ArrayList<ClaimEntity> findClaimByContract(ContractEntity contract);
}
