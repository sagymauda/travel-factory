package com.example.travelfactory.service;

import com.example.travelfactory.Entity.Campaign;
import com.example.travelfactory.Entity.Registration;
import com.example.travelfactory.repository.CampaignRepository;
import com.example.travelfactory.repository.RegistrationRepository;
import com.example.travelfactory.validation.ValidateRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ValidateRegistration validateRegistration;


    public Registration save(Registration registration, Long campaignId) throws ValidationException {
        Campaign campaign = campaignRepository.findById(campaignId).orElse(null);
        validateRegistration.validateExist(registration);

        if (campaign != null) {
            String[] mandatoryFields = campaign.getMandatoryFields().split("[\\s,]+");
            validateRegistration.validateData(registration, mandatoryFields);
        }
        List<Registration> regi = new ArrayList<>();
        Registration registration1 = registrationRepository.save(registration);
        regi.add(registration1);
        campaign.setRegistrations(regi);
        campaignRepository.save(campaign);

        return registration1;
    }


    public Registration getById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }
}


