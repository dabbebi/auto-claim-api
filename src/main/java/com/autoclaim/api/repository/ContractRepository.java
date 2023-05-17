package com.autoclaim.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.ContractEntity;
import com.autoclaim.api.entity.UserEntity;

@Repository
public interface ContractRepository extends CrudRepository<ContractEntity, Long> {
	public ContractEntity findContractByPublicId(String publicId);
	public ArrayList<ContractEntity> findContractByUser(UserEntity user);
}
