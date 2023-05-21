package com.autoclaim.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ContractEntity;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<ContractEntity, Long> {
	public ContractEntity findContractByContractNo(String contractNo);

	@Query("select max(t.id) from ContractEntity t")
	public int getMaxId();
}
