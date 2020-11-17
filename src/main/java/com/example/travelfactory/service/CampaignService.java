package com.example.travelfactory.service;

import com.example.travelfactory.Entity.Campaign;
import com.example.travelfactory.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository  campaignRepository;
    public Campaign save(Campaign campaign) {
       return campaignRepository.save(campaign);

    }

    public Campaign findById(Long campaignId) {
        return campaignRepository.findById(campaignId).orElse(null);
    }

    public void delete(Campaign campaign) {
         campaignRepository.delete(campaign);
    }
}
