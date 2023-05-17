package com.autoclaim.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ClaimEntity;

@Repository
public interface ClaimRepository extends CrudRepository<ClaimEntity, Long> {
	public ClaimEntity findClaimByPublicId(String publicId);
}
