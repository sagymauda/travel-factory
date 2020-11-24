package com.example.travelfactory.controller;


import com.example.travelfactory.Entity.Registration;

import com.example.travelfactory.dto.RegistrationDto;
import com.example.travelfactory.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(value = "/marketing/ws/partner")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationDto registrationDto;


    @PostMapping(value = "/campaign/{campaignId}/registration")
    public RegistrationDto createRegistration(@RequestBody RegistrationDto registrationDto , @PathVariable(value = "campaignId", required = true) Long campaignId){
        Registration registration1;
        RegistrationDto  registrationDto1 = null;
        try {
             registration1 = registrationService.save(registrationDto.convertRegistrationDtoToRegistration(registrationDto) , campaignId);
                registrationDto1 = registrationDto.convertRegistrationToDto(registration1);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

return registrationDto1;

    }
    @GetMapping(value = "/registration/{id}")
    public Registration getRegistration(@PathVariable (value = "id",required = true) Long id){
        return registrationService.getById(id);
    }
}
