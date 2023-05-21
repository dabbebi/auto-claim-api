package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;


public interface ClaimService {
	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel contract);
	public ClaimDetailsResponseModel updateClaim(String publicId, ClaimDetailsRequestModel claim);
	public ClaimDetailsResponseModel getClaim(String publicId);
	public ClaimDetailsResponseModel deleteClaim(String publicId);
	public ArrayList<ClaimDetailsResponseModel> getContractClaims(String contractId);
	public ArrayList<ClaimDetailsResponseModel> getAllClaims();
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit);
}
