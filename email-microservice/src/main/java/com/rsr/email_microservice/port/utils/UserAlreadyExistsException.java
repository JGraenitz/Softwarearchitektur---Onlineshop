package com.rsr.email_microservice.port.utils;


public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("The User you are trying to store already exists");
    }
}
