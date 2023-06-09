package com.autoclaim.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		return userService.getUserByPublicId(id);
	}
	
	@GetMapping(path = "/all")
	public ArrayList<UserDetailsResponseModel> getAllUsers() {	
		return userService.getAllUsers();
	}

	@GetMapping
	public ArrayList<UserDetailsResponseModel> getSomeUsers(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		return userService.getSomeUsers(page, limit);
	}
	
	@PostMapping
	public UserDetailsResponseModel createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		
		return userService.createUser(userDetails);
	}
	
	@PutMapping(path="/{id}")
	public UserDetailsResponseModel updateUser(@PathVariable String id, @Valid @RequestBody UserDetailsRequestModel userDetails) {
		return userService.updateUser(id, userDetails);
	}
	
	@PutMapping(path="/password/{id}")
	public UserDetailsResponseModel updateUserPassword(@PathVariable String id, @Valid @RequestBody UserPwdUpdateRequestModel passwordDetails) {
		return userService.updatePassword(id, passwordDetails);
	}
	
	@DeleteMapping(path="/{id}")
	public UserDetailsResponseModel deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
