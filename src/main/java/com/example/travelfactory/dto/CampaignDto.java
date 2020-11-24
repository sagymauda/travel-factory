package com.example.travelfactory.dto;

import com.example.travelfactory.Entity.Campaign;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Data
@Component
public class CampaignDto {


    public CampaignDto convertCampaignToDto(Campaign campaign){
        CampaignDto campaignDto = new CampaignDto();
        BeanUtils.copyProperties(campaign,campaignDto);
 return  campaignDto;
    }

    public Campaign convertCampaignDtoToCampaign(CampaignDto campaignDto){
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignDto , campaign);
        return campaign;
    }


}
