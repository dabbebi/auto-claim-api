package com.autoclaim.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.Contract;

import java.util.ArrayList;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long> {

	Contract findContractByContractNo(String contractNo);

	@Query("select t.contractNo from Contract t where upper(t.contractNo) like upper(:id || '%')")
	ArrayList<String> getAllContractNo(String id);
}
