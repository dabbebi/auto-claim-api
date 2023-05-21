package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;


public interface ClaimService {
	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel contract);
	public ClaimDetailsResponseModel updateClaim(String claimNo, ClaimDetailsRequestModel claim);
	public ClaimDetailsResponseModel getClaim(String claimNo);
	public ClaimDetailsResponseModel deleteClaim(String claimNo);
	public ArrayList<ClaimDetailsResponseModel> getContractClaims(String contractNo);
	public ArrayList<ClaimDetailsResponseModel> getAllClaims();
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit);
}
