package com.asu.cse545.group12.validator;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.domain.Users;

import org.springframework.validation.ValidationUtils;


public class CreateExternalUserValidator implements Validator{

    public boolean supports(Class aClass) {
        //just validate the Login instances
        return Users.class.isAssignableFrom(aClass);
    }

    public void validate(Object obj, Errors errors) {
    	Users user = (Users) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","required-username", "Enter username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required-password", "Enter password");    
    }   
}

