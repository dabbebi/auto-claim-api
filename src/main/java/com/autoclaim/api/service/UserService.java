package com.autoclaim.api.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.autoclaim.api.model.dto.UserDto;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto user);
	public UserDto getUser(String email);
	public UserDto getUserByPublicId(String publicId);
	public ArrayList<UserDto> getAllUsers();
	public UserDto updateUser(String publicId, UserDto user);
	public UserDto updatePassword(String publicId, UserPwdUpdateRequestModel passwordDetails);
	public UserDto deleteUser(String publicId);
}
