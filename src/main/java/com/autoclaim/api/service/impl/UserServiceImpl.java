package com.autoclaim.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.User;
import com.autoclaim.api.model.request.UserDetailsRequestModel;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;
import com.autoclaim.api.model.response.UserDetailsResponseModel;
import com.autoclaim.api.repository.UserRepository;
import com.autoclaim.api.service.UserService;
import com.autoclaim.api.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDetailsResponseModel createUser(UserDetailsRequestModel user) {

		if(userRepository.findUserByEmail(user.getEmail()) != null)
			throw new RuntimeException("Email already in use!");
		
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setPublicId(utils.generateRandomString(30));
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		User createdUser = userRepository.save(userEntity);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<GrantedAuthority>());
	}

	public UserDetailsResponseModel getUser(String email) {
		User userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDetailsResponseModel userDto = new UserDetailsResponseModel();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	public UserDetailsResponseModel getUserByPublicId(String publicId) {

		User storedUser = userRepository.findUserByPublicId(publicId);
		
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(storedUser, returnValue);
		return returnValue;
	}

	public ArrayList<UserDetailsResponseModel> getAllUsers() {
		
		ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();
		ArrayList<UserDetailsResponseModel> returnValue = new ArrayList<UserDetailsResponseModel>();
		
		for(User userEntity: allUsers) {
			UserDetailsResponseModel tempUser = new UserDetailsResponseModel();
			BeanUtils.copyProperties(userEntity, tempUser);
			returnValue.add(tempUser);
		}
		return returnValue;
	}

	@Override
	public ArrayList<UserDetailsResponseModel> getSomeUsers(int page, int limit) {
		Pageable pageableRequest = (Pageable) PageRequest.of(page, limit);
		Page<User> usersPage = userRepository.findAll(pageableRequest);
		List<User> allUsers = usersPage.getContent();
		ArrayList<UserDetailsResponseModel> returnValue = new ArrayList<>();

		for(User userEntity: allUsers) {
			UserDetailsResponseModel tempUser = new UserDetailsResponseModel();
			BeanUtils.copyProperties(userEntity, tempUser);
			returnValue.add(tempUser);
		}
		return returnValue;
	}

	public UserDetailsResponseModel updateUser(String publicId, UserDetailsRequestModel user) {
		
		User storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		storedUser.setAddress(user.getAddress());
		storedUser.setFirstName(user.getFirstName());
		storedUser.setLastName(user.getLastName());
		storedUser.setTelephone(user.getTelephone());
		
		User updatedUser = userRepository.save(storedUser);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}

	public UserDetailsResponseModel deleteUser(String publicId) {
		
		User storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		userRepository.delete(storedUser);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

	public UserDetailsResponseModel updatePassword(String publicId, UserPwdUpdateRequestModel passwordDetails) {

		User storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		String stoderPassword = storedUser.getPassword();
		String providedPassword = passwordDetails.getOldPassword();
		if(!bCryptPasswordEncoder.matches(providedPassword, stoderPassword))
			throw new RuntimeException("Provided password for user with public id " + publicId + " is wrong!");
		
		storedUser.setPassword(bCryptPasswordEncoder.encode(passwordDetails.getNewPassword()));
		userRepository.save(storedUser);
		
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

}
