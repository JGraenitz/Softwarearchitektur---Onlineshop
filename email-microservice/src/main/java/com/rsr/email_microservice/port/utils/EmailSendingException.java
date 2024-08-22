package com.rsr.email_microservice.port.utils;

public class EmailSendingException extends Exception {
    public EmailSendingException() {
        super("Failed to send Email");
    }
}
