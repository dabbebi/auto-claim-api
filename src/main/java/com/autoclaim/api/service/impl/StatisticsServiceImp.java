package com.autoclaim.api.service.impl;

import com.autoclaim.api.model.response.StatisticsDetailsResponseModel;
import com.autoclaim.api.repository.StatRepository;
import com.autoclaim.api.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatisticsServiceImp implements StatisticsService {

    @Autowired
    StatRepository statRepository;


    @Override
    public StatisticsDetailsResponseModel getStat() {
        StatisticsDetailsResponseModel returnValue = new StatisticsDetailsResponseModel();
        returnValue.setUserCount(statRepository.getUserCount());
        returnValue.setExpiredContractCount(statRepository.getExpiredContractCount());
        returnValue.setValidContractCount(statRepository.getValidContractCount());
        returnValue.setExpertiseClaimCount(statRepository.getExpertiseClaimCount());
        returnValue.setOpenClaimCount(statRepository.getOpenClaimCount());
        returnValue.setFinishedClaimCount(statRepository.getFinishedClaimCount());

        ArrayList<Long> claimsPerMonth = new ArrayList<>();

        for(int i = 1; i <= 12; i++) {
            claimsPerMonth.add(statRepository.getClaimOnMonth(i));
        }

        returnValue.setClaimsPerMonth(claimsPerMonth);

        return returnValue;
    }
}
