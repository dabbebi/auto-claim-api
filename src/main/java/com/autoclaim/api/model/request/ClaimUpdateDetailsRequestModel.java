package com.autoclaim.api.model.request;

import com.autoclaim.api.enums.ClaimStatus;
import com.autoclaim.api.model.response.PictureDetailsResponseModel;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Validated
public class ClaimUpdateDetailsRequestModel {
    @NotNull
    private Date accidentDate;

    @NotNull
    private String contractNo;

    @NotNull
    private ClaimStatus status;

    private ArrayList<PictureDetailsRequestModel> addedPictures;

    private ArrayList<PictureDetailsResponseModel> removedPictures;

    public ArrayList<PictureDetailsResponseModel> getRemovedPictures() {
        return removedPictures;
    }

    public void setRemovedPictures(ArrayList<PictureDetailsResponseModel> removedPictures) {
        this.removedPictures = removedPictures;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }
    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }
    public String getContractNo() {
        return contractNo;
    }
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public ArrayList<PictureDetailsRequestModel> getAddedPictures() {
        return addedPictures;
    }
    public void setAddedPictures(ArrayList<PictureDetailsRequestModel> addedPictures) {
        this.addedPictures = addedPictures;
    }

    public ClaimStatus getStatus() {
        return this.status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }
}
