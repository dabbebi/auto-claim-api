package com.autoclaim.api.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.UserEntity;
import com.autoclaim.api.model.dto.UserDto;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;
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
	
	public UserDto createUser(UserDto user) {

		if(userRepository.findUserByEmail(user.getEmail()) != null)
			throw new RuntimeException("Email already in use!");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setPublicId(utils.generateRandomString(30));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserDto returnValue = new UserDto();
		UserEntity createdUser = userRepository.save(userEntity);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<GrantedAuthority>());
	}

	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	public UserDto getUserByPublicId(String publicId) {

		UserEntity storedUser = userRepository.findUserByPublicId(publicId);
		
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUser, returnValue);
		return returnValue;
	}

	public ArrayList<UserDto> getAllUsers() {
		
		ArrayList<UserEntity> allUsers = (ArrayList<UserEntity>) userRepository.findAll();
		ArrayList<UserDto> returnValue = new ArrayList<UserDto>();
		
		for(int i = 0; i < allUsers.size(); i++) {
			UserDto tempUser = new UserDto();
			BeanUtils.copyProperties(allUsers.get(i), tempUser);
			returnValue.add(tempUser);
		}
		return returnValue;
	}

	public UserDto updateUser(String publicId, UserDto user) {
		
		UserEntity storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		storedUser.setAddress(user.getAddress());
		storedUser.setFirstName(user.getFirstName());
		storedUser.setLastName(user.getLastName());
		storedUser.setTelephone(user.getTelephone());
		
		UserEntity updatedUser = userRepository.save(storedUser);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}

	public UserDto deleteUser(String publicId) {
		
		UserEntity storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		userRepository.delete(storedUser);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

	public UserDto updatePassword(String publicId, UserPwdUpdateRequestModel passwordDetails) {

		UserEntity storedUser= userRepository.findUserByPublicId(publicId);
		if(storedUser == null) throw new RuntimeException("User with public id " + publicId + " not found!");
		
		String stoderPassword = storedUser.getEncryptedPassword();
		String providedPassword = passwordDetails.getOldPassword();
		if(!bCryptPasswordEncoder.matches(providedPassword, stoderPassword))
			throw new RuntimeException("Provided password for user with public id " + publicId + " is wrong!");
		
		storedUser.setEncryptedPassword(bCryptPasswordEncoder.encode(passwordDetails.getNewPassword()));
		userRepository.save(storedUser);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
	}

}
