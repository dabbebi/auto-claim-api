package com.autoclaim.api.service;

import java.util.ArrayList;

import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;

public interface ClaimService {
	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel contract);
	public ClaimDetailsResponseModel updateClaim(String publicId, ClaimDetailsRequestModel contract);
	public ClaimDetailsResponseModel getClaim(String publicId);
	public ClaimDetailsResponseModel deleteClaim(String publicId);
	public ArrayList<ClaimDetailsResponseModel> getAllClaims(String contractId);
}
