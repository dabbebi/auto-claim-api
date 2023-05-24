package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;


public interface ClaimService {
	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel contract);
	public ClaimDetailsResponseModel updateClaim(String claimNo, ClaimDetailsRequestModel claim);
	public ClaimDetailsResponseModel getClaim(String claimNo);
	public ClaimDetailsResponseModel deleteClaim(String claimNo);
	public ArrayList<ClaimDetailsResponseModel> getAllContractClaims(String contractNo);
	public ArrayList<ClaimDetailsResponseModel> getSomeContractClaims(String contractNo, int page, int limit);
	public ArrayList<ClaimDetailsResponseModel> getAllClaims();
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit);
	public ArrayList<ClaimDetailsResponseModel> deleteMultipleClaims(ArrayList<String> claims);
}
