package com.autoclaim.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;
import com.autoclaim.api.service.ClaimService;

@RestController
@RequestMapping("claim") // http://localhost:8080/claim
public class ClaimController {
	
	@Autowired
	ClaimService claimService;
	
	@PostMapping
	public ClaimDetailsResponseModel createContract(@Valid @RequestBody ClaimDetailsRequestModel claim) {
		return claimService.createClaim(claim);
	}
	
	@GetMapping(path="/{id}")
	public ClaimDetailsResponseModel getContract(@PathVariable String id) {
		return claimService.getClaim(id);
	}
	
	@GetMapping(path="/contract/{id}")
	public ArrayList<ClaimDetailsResponseModel> getContractClaims(@PathVariable String id) {
		return claimService.getContractClaims(id);
	}
	
	@PutMapping(path="/{id}")
	public ClaimDetailsResponseModel updateContract(@PathVariable String id, @Valid @RequestBody ClaimDetailsRequestModel claim) {
		return claimService.updateClaim(id, claim);
	}
	
	@DeleteMapping(path="/{id}")
	public ClaimDetailsResponseModel deleteContract(@PathVariable String id) {
		return claimService.deleteClaim(id);
	}
	

}
