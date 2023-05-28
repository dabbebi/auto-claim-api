package com.autoclaim.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.autoclaim.api.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserByPublicId(String publicId);
}
