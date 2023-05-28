package com.autoclaim.api.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.autoclaim.api.model.request.UserDetailsRequestModel;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;
import com.autoclaim.api.model.response.UserDetailsResponseModel;

public interface UserService extends UserDetailsService {
	UserDetailsResponseModel createUser(UserDetailsRequestModel user);
	UserDetailsResponseModel getUser(String email);
	UserDetailsResponseModel getUserByPublicId(String publicId);
	ArrayList<UserDetailsResponseModel> getAllUsers();
	ArrayList<UserDetailsResponseModel> getSomeUsers(int page, int limit);
	UserDetailsResponseModel updateUser(String publicId, UserDetailsRequestModel user);
	UserDetailsResponseModel updatePassword(String publicId, UserPwdUpdateRequestModel passwordDetails);
	UserDetailsResponseModel deleteUser(String publicId);
}
