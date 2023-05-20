package com.autoclaim.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ContractEntity;

@Repository
public interface ContractRepository extends CrudRepository<ContractEntity, Long> {
	public ContractEntity findContractByPublicId(String publicId);
}
