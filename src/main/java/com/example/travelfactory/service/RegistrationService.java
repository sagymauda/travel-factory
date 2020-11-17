package com.example.travelfactory.service;

import com.example.travelfactory.Entity.Campaign;
import com.example.travelfactory.Entity.Registration;
import com.example.travelfactory.repository.CampaignRepository;
import com.example.travelfactory.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.bind.ValidationException;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    CampaignRepository campaignRepository;


    public Registration save(Registration registration, Long campaignId) throws ValidationException {
        Campaign campaign = campaignRepository.findById(campaignId).orElse(null);

        //need to check if registration is in the db if not throw 200 ok , else throw 409

        if (campaign != null) {
            String[] mandatoryFields = campaign.getMandatoryFields().split("[\\s,]+");
            validate(registration, mandatoryFields);
        }
        List<Registration> regi = new ArrayList<>();
           Registration registration1 = registrationRepository.save(registration);
            regi.add(registration1);

            campaign.setRegistrations(regi);
        campaignRepository.save(campaign);

        return registration1 ;
    }

    private void validate(Registration registration, String[] mandatoryFields) throws ValidationException {
        for (String mandatoryField : mandatoryFields) {
            switch (mandatoryField) {
                case "name":
                    if (registration.getName() == null) {
                        throw new ValidationException(" must be added");
                    }
                    break;
                case "phone":
                    if (registration.getPhone() != null) {
                        //here i check for the regex
                        Pattern pattern = Pattern.compile(" ^ [+] * [(] {0,1} [0-9] {1,4} [)] {0,1} [- \\ s \\ ./ 0-9] * $");
                        Matcher matcher = pattern.matcher(registration.getPhone());
                        if(!matcher.matches()){
                            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "wrong format for field "+mandatoryField);
                        }


                    } else {
                        throw new ValidationException("phone must be added");
                    }
                    break;
                case "email":
                    if (registration.getEmail() != null) {
                        //regex ver
                        Pattern pattern = Pattern.compile("^ [\\ w - .] + @ ( [\\ w -] + .) + [\\ w -] {2,4} $ ");
                        Matcher matcher = pattern.matcher(registration.getEmail());
                        if(!matcher.matches()){
                            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "wrong format for field "+mandatoryField);
                        }
                    } else {
                        throw new ValidationException("email must be added");
                    }
                    break;
                case "firstName":

                    if (registration.getFirstName() == null) {
                        throw new ValidationException("first name must be added");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + mandatoryField);
            }
        }



    }

    public Registration getById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }
}

//
//        Registration registration1 = registrationRepository.save(registration);
//        List<Registration> registrationList = new ArrayList<>();
//        registrationList.add(registration1);
//
//        campaign.setRegistration(registrationList);
//
//        return registration1;
//    }

