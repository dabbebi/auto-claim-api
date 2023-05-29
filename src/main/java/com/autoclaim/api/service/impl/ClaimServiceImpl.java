package com.autoclaim.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.autoclaim.api.entity.Picture;
import com.autoclaim.api.model.request.ClaimUpdateDetailsRequestModel;
import com.autoclaim.api.model.request.PictureDetailsRequestModel;
import com.autoclaim.api.model.response.PictureDetailsResponseModel;
import com.autoclaim.api.repository.PictureRepository;
import com.autoclaim.api.shared.FileUtils;
import com.autoclaim.api.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.autoclaim.api.entity.Claim;
import com.autoclaim.api.entity.Contract;
import com.autoclaim.api.model.request.ClaimDetailsRequestModel;
import com.autoclaim.api.model.response.ClaimDetailsResponseModel;
import com.autoclaim.api.repository.ClaimRepository;
import com.autoclaim.api.repository.ContractRepository;
import com.autoclaim.api.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	private Utils utils;

	@Autowired
	ModelMapper modelMapper;

	public ClaimDetailsResponseModel createClaim(ClaimDetailsRequestModel claim) {
		Contract contract = contractRepository.findContractByContractNo(claim.getContractNo());
		if(contract == null) throw new RuntimeException("Contract with number " + claim.getContractNo() + " not found!");

		Claim claimEntity = new Claim();
		BeanUtils.copyProperties(claim, claimEntity);

		claimEntity.setCreationDate(new Date());
		claimEntity.setContract(contract);

		if(claim.getPictures() != null) {
			Set<Picture> pictures = new HashSet<>();
			for(PictureDetailsRequestModel picture: claim.getPictures()) {
				Picture newPicture = new Picture();
				newPicture.setPublicId(utils.generateRandomString(30));
				newPicture.setPath(picture.getPath());
				pictures.add(newPicture);
			}
			claimEntity.setPictures(pictures);
		}

		Claim createdClaim = claimRepository.save(claimEntity);

		ClaimDetailsResponseModel returnValue = modelMapper.map(createdClaim, ClaimDetailsResponseModel.class);

		return returnValue;
	}

	public ClaimDetailsResponseModel updateClaim(String claimNo, ClaimUpdateDetailsRequestModel claim) throws IOException {
		Claim storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with number " + claimNo + " not found!");

		Contract contract = contractRepository.findContractByContractNo(claim.getContractNo());
		if(contract == null) throw new RuntimeException("Contract with number " + claim.getContractNo() + " not found!");

		BeanUtils.copyProperties(claim, storedClaim);
		storedClaim.setContract(contract);

		// Add the new pictures from claim.addedPictures
		Set<Picture> pictures = new HashSet<>();
		if(claim.getAddedPictures() != null) {
			for(PictureDetailsRequestModel picture: claim.getAddedPictures()) {
				Picture newPicture = new Picture();
				newPicture.setPublicId(utils.generateRandomString(30));
				newPicture.setPath(picture.getPath());
				pictures.add(newPicture);
			}
		}

		// Remove pictures existing in claim.removedPictures
		if(storedClaim.getPictures() != null && claim.getRemovedPictures() != null) {
			for(Picture picture: storedClaim.getPictures()) {
				FileUtils fileUtils = new FileUtils();
				String pictureId = picture.getPublicId();
				Boolean addIt = true;
				for(PictureDetailsResponseModel pic: claim.getRemovedPictures()) {
					if(pictureId.equals(pic.getPublicId())) {
						addIt = false;
						Picture storedPic = pictureRepository.findPictureByPublicId(pictureId);
						if(storedPic != null) {
							fileUtils.deleteFile(storedPic.getPath());
						}
						break;
					}
				}
				if(addIt) {
					pictures.add(picture);
				}
			}
		}
		storedClaim.setPictures(pictures);

		claimRepository.save(storedClaim);

		ClaimDetailsResponseModel returnValue = modelMapper.map(storedClaim, ClaimDetailsResponseModel.class);

		return returnValue;
	}

	public ClaimDetailsResponseModel getClaim(String claimNo) {
		Claim storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with number " + claimNo + " not found!");

		ClaimDetailsResponseModel returnValue = modelMapper.map(storedClaim, ClaimDetailsResponseModel.class);

		return returnValue;
	}

	public ClaimDetailsResponseModel deleteClaim(String claimNo) {
		Claim storedClaim = claimRepository.findClaimByClaimNo(claimNo);
		if(storedClaim == null) throw new RuntimeException("Claim with number " + claimNo + " not found!");

		claimRepository.delete(storedClaim);

		ClaimDetailsResponseModel returnValue = new ClaimDetailsResponseModel();
		BeanUtils.copyProperties(storedClaim, returnValue);

		return returnValue;
	}

	public ArrayList<ClaimDetailsResponseModel> getAllContractClaims(String contractNo) {
		Contract contract = contractRepository.findContractByContractNo(contractNo);
		if(contract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");

		ArrayList<Claim> allClaims = claimRepository.findClaimByContract(contract);

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();

		for(Claim claim : allClaims) {
			ClaimDetailsResponseModel tempClaim = modelMapper.map(claim, ClaimDetailsResponseModel.class);
			returnValue.add(tempClaim);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> getSomeContractClaims(String contractNo, int page, int limit) {
		Contract contract = contractRepository.findContractByContractNo(contractNo);
		if(contract == null) throw new RuntimeException("Contract with number " + contractNo + " not found!");

		Pageable pageRequest = PageRequest.of(page, limit);
		ArrayList<Claim> allClaims = claimRepository.findClaimPageByContract(contract, pageRequest);

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();

		for(Claim claim : allClaims) {
			ClaimDetailsResponseModel tempClaim = modelMapper.map(claim, ClaimDetailsResponseModel.class);
			returnValue.add(tempClaim);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> getAllClaims() {

		ArrayList<Claim> allClaims = (ArrayList<Claim>) claimRepository.findAll();

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();

		for(Claim claim : allClaims) {
			ClaimDetailsResponseModel tempClaim = modelMapper.map(claim, ClaimDetailsResponseModel.class);
			returnValue.add(tempClaim);
		}
		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> getSomeClaims(int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<Claim> claimEntityPage = claimRepository.findAll(pageableRequest);

		ArrayList<Claim> allClaims = (ArrayList<Claim>) claimEntityPage.getContent();

		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();

		for(Claim claim : allClaims) {
			ClaimDetailsResponseModel tempClaim = modelMapper.map(claim, ClaimDetailsResponseModel.class);
			returnValue.add(tempClaim);
		}

		return returnValue;
	}

	@Override
	public ArrayList<ClaimDetailsResponseModel> deleteMultipleClaims(ArrayList<String> claims) {
		ArrayList<ClaimDetailsResponseModel> returnValue = new ArrayList<>();
		for(String claim: claims) {
			Claim storedClaim = claimRepository.findClaimByClaimNo(claim);
			if(storedClaim == null) throw new RuntimeException("Claim with number " + claim + " not found!");

			claimRepository.delete(storedClaim);

			ClaimDetailsResponseModel currentClaim = new ClaimDetailsResponseModel();
			BeanUtils.copyProperties(storedClaim, currentClaim);
			returnValue.add(currentClaim);
		}
		return returnValue;
	}

}