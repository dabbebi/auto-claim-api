package com.autoclaim.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ClaimEntity;
import com.autoclaim.api.entity.ContractEntity;

@Repository
public interface ClaimRepository extends CrudRepository<ClaimEntity, Long> {
	public ClaimEntity findClaimByPublicId(String publicId);
	public ArrayList<ClaimEntity> findClaimByContract(ContractEntity contract);
}
