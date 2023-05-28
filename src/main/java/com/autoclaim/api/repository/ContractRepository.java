package com.autoclaim.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.Contract;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long> {

	Contract findContractByContractNo(String contractNo);

	@Query("select COALESCE(max(t.id), 0) from Contract t")
	int getMaxId();

	@Query("select count(*) from Contract t")
	int getContractCount();
}
