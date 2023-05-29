package com.autoclaim.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.service.ContractService;

@RestController
@RequestMapping("contract") // http://localhost:8080/contract
public class ContractController {
	
	@Autowired
	ContractService contractService;
	
	@PostMapping
	public ContractDetailsResponseModel createContract(@Valid @RequestBody ContractDetailsRequestModel contract) {
		return contractService.createContract(contract);
	}

	@PostMapping(path = "/delete")
	public ArrayList<ContractDetailsResponseModel> deleteMultipleClaims(@RequestBody ArrayList<String> contracts) {
		return contractService.deleteMultipleContracts(contracts);
	}
	
	@GetMapping(path="/{id}")
	public ContractDetailsResponseModel getContract(@PathVariable String id) {
		return contractService.getContract(id);
	}
	
	@GetMapping(path = "/all")
	public ArrayList<ContractDetailsResponseModel> getAllContracts() {
		return contractService.getAllContracts();
	}

	@GetMapping
	public ArrayList<ContractDetailsResponseModel> getSomeContracts(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		return contractService.getSomeContracts(page, limit);
	}

	@GetMapping(path = "/contractNo/{id}")
	ArrayList<String> getAllContractNo(@PathVariable String id) {
		return contractService.getALlContractNo(id);
	}
	
	@PutMapping(path="/{id}")
	public ContractDetailsResponseModel updateContract(@PathVariable String id, @Valid @RequestBody ContractDetailsRequestModel contract) {
		return contractService.updateContract(id, contract);
	}
	
	@DeleteMapping(path="/{id}")
	public ContractDetailsResponseModel deleteContract(@PathVariable String id) {
		return contractService.deleteContract(id);
	}
}