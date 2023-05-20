package com.autoclaim.api.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.autoclaim.api.model.request.UserDetailsRequestModel;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;
import com.autoclaim.api.model.response.UserDetailsResponseModel;

public interface UserService extends UserDetailsService {
	public UserDetailsResponseModel createUser(UserDetailsRequestModel user);
	public UserDetailsResponseModel getUser(String email);
	public UserDetailsResponseModel getUserByPublicId(String publicId);
	public ArrayList<UserDetailsResponseModel> getAllUsers();
	public UserDetailsResponseModel updateUser(String publicId, UserDetailsRequestModel user);
	public UserDetailsResponseModel updatePassword(String publicId, UserPwdUpdateRequestModel passwordDetails);
	public UserDetailsResponseModel deleteUser(String publicId);
}
