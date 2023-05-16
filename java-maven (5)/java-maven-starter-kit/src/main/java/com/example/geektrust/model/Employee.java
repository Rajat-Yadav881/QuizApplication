package com.example.geektrust.model;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.exception.InvalidInputException;

public class Employee {
    private final String name;
    private final String emailAdress;

    public Employee(String emailAdress) throws InvalidInputException{
        if(Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailAdress).matches()){
            this.emailAdress = emailAdress;
            this.name = this.emailAdress.substring(0,this.emailAdress.indexOf('@'));
        }else{
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmailAdress() {
        return emailAdress;
    }
}
