package com.autoclaim.api.controller;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoclaim.api.model.dto.UserDto;
import com.autoclaim.api.model.request.UserDetailsRequestModel;
import com.autoclaim.api.model.request.UserPwdUpdateRequestModel;
import com.autoclaim.api.model.response.UserDetailsResponseModel;
import com.autoclaim.api.service.UserService;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping(path="/{id}")
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		
		UserDto userDto = userService.getUserByPublicId(id);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@GetMapping
	public ArrayList<UserDetailsResponseModel> getAllUsers() {
		
		ArrayList<UserDto> allStoredusers = userService.getAllUsers();
		ArrayList<UserDetailsResponseModel> returnValue = new ArrayList<UserDetailsResponseModel>();
		
		for(int i = 0; i < allStoredusers.size(); i++) {
			UserDetailsResponseModel tempUser = new UserDetailsResponseModel();
			BeanUtils.copyProperties(allStoredusers.get(i), tempUser);
			returnValue.add(tempUser);
		}
		
		return returnValue;
	}
	
	@PostMapping
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
	public UserDetailsResponseModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser = userService.updateUser(id, userDto);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/password/{id}")
	public UserDetailsResponseModel updateUserPassword(@PathVariable String id, @RequestBody UserPwdUpdateRequestModel passwordDetails) {
		
		UserDto updatedUser = userService.updatePassword(id, passwordDetails);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public UserDetailsResponseModel deleteUser(@PathVariable String id) {
		UserDto deletedUser = userService.deleteUser(id);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		BeanUtils.copyProperties(deletedUser, returnValue);
		
		return returnValue;
	}
}
