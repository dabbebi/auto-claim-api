package com.autoclaim.api.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.Claim;
import com.autoclaim.api.entity.Contract;

@Repository
public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long> {
	Claim findClaimByClaimNo(String claimNo);

	@Query("select COALESCE(max(t.id), 0) from Claim t")
	int getMaxId();

	@Query("select count(*) from Claim t")
	int getClaimCount();

	ArrayList<Claim> findClaimByContract(Contract contract);

	ArrayList<Claim> findClaimPageByContract(Contract contract, Pageable pageable);
}
