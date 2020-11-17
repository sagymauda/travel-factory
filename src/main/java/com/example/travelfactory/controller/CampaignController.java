package com.example.travelfactory.controller;

import com.example.travelfactory.Entity.Campaign;
import com.example.travelfactory.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/marketing/ws/partner")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;


    @PostMapping(value = "/campaign")
    public Campaign CreateCampaign(@RequestBody Campaign campaign){
       return campaignService.save(campaign);
    }

    @GetMapping(value ="/campaign/{campaignId}")
    public Campaign getCampaign(@PathVariable(value = "campaignId", required = true) Long campaignId){
        return campaignService.findById(campaignId);

    }

    @DeleteMapping(value ="/campaign")
    public void deleteCampaign(@RequestBody Campaign campaign){
         campaignService.delete(campaign);
    }


}
