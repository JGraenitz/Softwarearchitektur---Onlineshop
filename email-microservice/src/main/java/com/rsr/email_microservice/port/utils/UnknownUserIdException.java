package com.rsr.email_microservice.port.utils;

public class UnknownUserIdException extends RuntimeException {
    public UnknownUserIdException() {
        super("No User with this ID");
    }
}
