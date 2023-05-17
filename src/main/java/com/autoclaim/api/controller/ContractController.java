package com.autoclaim.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoclaim.api.model.request.ContractDetailsRequestModel;
import com.autoclaim.api.model.response.ContractDetailsResponseModel;
import com.autoclaim.api.service.ContractService;

@RestController
@RequestMapping("contract") // http://localhost:8080/contract
public class ContractController {
	
	@Autowired
	ContractService contractService;
	
	@PostMapping
	public ContractDetailsResponseModel createContract(@RequestBody ContractDetailsRequestModel contract) {
		return contractService.createContract(contract);
	}
	
	@GetMapping(path="/{id}")
	public ContractDetailsResponseModel getContract(@PathVariable String id) {
		return contractService.getContract(id);
	}
	
	@GetMapping(path="/user/{id}")
	public ArrayList<ContractDetailsResponseModel> getAllContracts(@PathVariable String id) {
		return contractService.getAllContract(id);
	}
	
	@PutMapping(path="/{id}")
	public ContractDetailsResponseModel updateContract(@PathVariable String id, @RequestBody ContractDetailsRequestModel contract) {
		return contractService.updateContract(id, contract);
	}
	
	@DeleteMapping(path="/{id}")
	public ContractDetailsResponseModel deleteContract(@PathVariable String id) {
		return contractService.deleteContract(id);
	}

}
