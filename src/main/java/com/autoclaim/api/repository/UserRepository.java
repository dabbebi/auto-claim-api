package com.autoclaim.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	public UserEntity findUserByEmail(String email);
	public UserEntity findUserByPublicId(String publicId);
}
