package com.autoclaim.api.model.response;

import java.util.ArrayList;

public class StatisticsDetailsResponseModel {
    private long userCount;
    private long validContractCount;
    private long expiredContractCount;
    private long openClaimCount;
    private long expertiseClaimCount;
    private long finishedClaimCount;
    private ArrayList<Long> claimsPerMonth;

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public void setValidContractCount(long validContractCount) {
        this.validContractCount = validContractCount;
    }

    public void setExpiredContractCount(long expiredContractCount) {
        this.expiredContractCount = expiredContractCount;
    }

    public void setOpenClaimCount(long openClaimCount) {
        this.openClaimCount = openClaimCount;
    }

    public void setExpertiseClaimCount(long expertiseClaimCount) {
        this.expertiseClaimCount = expertiseClaimCount;
    }

    public void setFinishedClaimCount(long finishedClaimCount) {
        this.finishedClaimCount = finishedClaimCount;
    }

    public void setClaimsPerMonth(ArrayList<Long> claimsPerMonth) {
        this.claimsPerMonth = claimsPerMonth;
    }

    public long getUserCount() {
        return userCount;
    }

    public long getValidContractCount() {
        return validContractCount;
    }

    public long getExpiredContractCount() {
        return expiredContractCount;
    }

    public long getOpenClaimCount() {
        return openClaimCount;
    }

    public long getExpertiseClaimCount() {
        return expertiseClaimCount;
    }

    public long getFinishedClaimCount() {
        return finishedClaimCount;
    }

    public ArrayList<Long> getClaimsPerMonth() {
        return claimsPerMonth;
    }
}
