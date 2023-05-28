package com.autoclaim.api.repository;

import com.autoclaim.api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends PagingAndSortingRepository<User, Long> {

    @Query("Select count(*) from User")
    long getUserCount();

    @Query("select count(*) from Contract t where t.endDate < current_date")
    long getExpiredContractCount();

    @Query("select count(*) from Contract t where t.endDate >= current_date")
    long getValidContractCount();

    @Query("select count(*) from Claim t where t.status = 0")
    long getOpenClaimCount();

    @Query("select count(*) from Claim t where t.status = 1")
    long getExpertiseClaimCount();

    @Query("select count(*) from Claim t where t.status = 2")
    long getFinishedClaimCount();

    @Query("select count(*) from Claim t where month(t.creationDate) = :month and year(t.creationDate) = year(current_date )")
    long getClaimOnMonth(int month);
}
