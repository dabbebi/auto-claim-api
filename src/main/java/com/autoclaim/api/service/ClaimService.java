package com.autoclaim.api.service;

import java.io.IOException;
import java.util.ArrayList;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.request.ClaimUpdateDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;


public interface ClaimService {
	ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel contract);
	ClaimDetailsResponseModel updateClaim(String claimNo, ClaimUpdateDetailsRequestModel claim) throws IOException;
	ClaimDetailsResponseModel getClaim(String claimNo);
	ClaimDetailsResponseModel deleteClaim(String claimNo);
	ArrayList<ClaimDetailsResponseModel> getAllContractClaims(String contractNo);
	ArrayList<ClaimDetailsResponseModel> getSomeContractClaims(String contractNo, int page, int limit);
	ArrayList<ClaimDetailsResponseModel> getAllClaims();
	ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit);
	ArrayList<ClaimDetailsResponseModel> deleteMultipleClaims(ArrayList<String> claims);
}
