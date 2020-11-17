package com.example.travelfactory.controller;


import com.example.travelfactory.Entity.Registration;

import com.example.travelfactory.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(value = "/marketing/ws/partner")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @PostMapping(value = "/campaign/{campaignId}/registration")
    public Registration createRegistration(@RequestBody Registration registration , @PathVariable(value = "campaignId", required = true) Long campaignId){
        Registration registration1= null;
//        try{
        try {
             registration1 = registrationService.save(registration , campaignId);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
//        }catch (ValidationException e){
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong format for field");
//        }
//
//        if(registration1== null){
//            return ResponseEntity.ok(registration1);
////        }else{
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
return registration1;

    }
    @GetMapping(value = "/registration/{id}")
    public Registration getRegistration(@PathVariable (value = "id",required = true) Long id){
        return registrationService.getById(id);
    }
}
