package com.autoclaim.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;
import com.autoclaim.api.service.ClaimService;

@RestController
@RequestMapping("claim") // http://localhost:8080/claim
public class ClaimController {
	
	@Autowired
	ClaimService claimService;
	
	@PostMapping
	public ClaimDetailsResponseModel createClaim(@Valid @RequestBody ClaimDetailsRequestModel claim) {
		return claimService.createClaim(claim);
	}
	
	@GetMapping(path="/{id}")
	public ClaimDetailsResponseModel getClaim(@PathVariable String id) {
		return claimService.getClaim(id);
	}

	@GetMapping(path="/all")
	public ArrayList<ClaimDetailsResponseModel> getAllClaims(@PathVariable String id) {
		return claimService.getAllClaims();
	}

	@GetMapping
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		return claimService.getSomeClaims(page, limit);
	}
	
	@GetMapping(path="/contract/all/{id}")
	public ArrayList<ClaimDetailsResponseModel> getAllContractClaims(@PathVariable String id) {
		return claimService.getContractClaims(id);
	}
	
	@PutMapping(path="/{id}")
	public ClaimDetailsResponseModel updateClaim(@PathVariable String id, @Valid @RequestBody ClaimDetailsRequestModel claim) {
		return claimService.updateClaim(id, claim);
	}
	
	@DeleteMapping(path="/{id}")
	public ClaimDetailsResponseModel deleteClaim(@PathVariable String id) {
		return claimService.deleteClaim(id);
	}
}
