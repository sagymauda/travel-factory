package com.example.travelfactory.controller;

import com.example.travelfactory.Entity.Campaign;
import com.example.travelfactory.dto.CampaignDto;
import com.example.travelfactory.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/marketing/ws/partner")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @Autowired
    CampaignDto campaignDto;



    @PostMapping(value = "/campaign")
    public CampaignDto CreateCampaign(@RequestBody CampaignDto campaignDto){
       return campaignDto.convertCampaignToDto(campaignService.save( campaignDto.convertCampaignDtoToCampaign(campaignDto)));
    }

    @GetMapping(value ="/campaign/{campaignId}")
    public CampaignDto getCampaign(@PathVariable(value = "campaignId", required = true) Long campaignId){
        return campaignDto.convertCampaignToDto(campaignService.findById(campaignId));

    }

    @DeleteMapping(value ="/campaign")
    public void deleteCampaign(@RequestBody Campaign campaign){
         campaignService.delete(campaign);
    }


}
