package com.example.travelfactory.validation;

import com.example.travelfactory.Entity.Registration;
import com.example.travelfactory.repository.RegistrationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.bind.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Data
public class ValidateRegistration {


    @Autowired
    RegistrationRepository registrationRepository;


    public void validateExist(Registration registration) throws HttpClientErrorException {
        registrationRepository.findAll();
        for (Registration r :
                registrationRepository.findAll()) {

            if (r.getName().equals(registration.getName())) {
                throw new HttpClientErrorException(HttpStatus.CONFLICT, "registration " + registration.getName() + "is all ready in dataBae");
            }

        }

    }


    public void validateData(Registration registration, String[] mandatoryFields) throws ValidationException {
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
                        if (!matcher.matches()) {
                            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "wrong format for field " + mandatoryField);
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
                        if (!matcher.matches()) {
                            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "wrong format for field " + mandatoryField);
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


}
