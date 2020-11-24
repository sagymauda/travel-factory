package com.example.travelfactory.dto;



import com.example.travelfactory.Entity.Registration;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegistrationDto {




    public RegistrationDto convertRegistrationToDto(Registration registration ){
        RegistrationDto registrationDto = new RegistrationDto();
        BeanUtils.copyProperties(registration, registrationDto);
        return  registrationDto;
    }

    public Registration convertRegistrationDtoToRegistration(RegistrationDto registrationDto ){
        Registration registration = new Registration();
        BeanUtils.copyProperties(registrationDto , registration);
        return registration;
    }
}
