package com.autoclaim.api.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import com.autoclaim.api.model.request.ClaimUpdateDetailsRequestModel;
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

	@PostMapping(path = "/delete")
	public ArrayList<ClaimDetailsResponseModel> deleteMultipleClaims(@RequestBody ArrayList<String> claims) {
		return claimService.deleteMultipleClaims(claims);
	}
	
	@GetMapping(path="/{id}")
	public ClaimDetailsResponseModel getClaim(@PathVariable String id) {
		return claimService.getClaim(id);
	}

	@GetMapping(path="/all")
	public ArrayList<ClaimDetailsResponseModel> getAllClaims() {
		return claimService.getAllClaims();
	}

	@GetMapping
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		return claimService.getSomeClaims(page, limit);
	}
	
	@GetMapping(path="/contract/all/{id}")
	public ArrayList<ClaimDetailsResponseModel> getAllContractClaims(@PathVariable String id) {
		return claimService.getAllContractClaims(id);
	}

	@GetMapping(path="/contract/{id}")
	public ArrayList<ClaimDetailsResponseModel> getSomeContractClaims(@PathVariable String id,
																	  @RequestParam(value = "page") int page,
																	  @RequestParam(value = "limit") int limit) {
		return claimService.getSomeContractClaims(id, page, limit);
	}
	
	@PutMapping(path="/{id}")
	public ClaimDetailsResponseModel updateClaim(@PathVariable String id, @Valid @RequestBody ClaimUpdateDetailsRequestModel claim) throws IOException {
		return claimService.updateClaim(id, claim);
	}
	
	@DeleteMapping(path="/{id}")
	public ClaimDetailsResponseModel deleteClaim(@PathVariable String id) {
		return claimService.deleteClaim(id);
	}
}
