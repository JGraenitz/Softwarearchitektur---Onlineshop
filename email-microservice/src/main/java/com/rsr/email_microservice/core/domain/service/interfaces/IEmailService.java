package com.rsr.email_microservice.core.domain.service.interfaces;

import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.port.user.dto.OrderDTO;
import com.rsr.email_microservice.port.user.dto.PaymentDTO;
import com.rsr.email_microservice.port.utils.EmailSendingException;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface IEmailService {

    void sendEmail(String recipient, String body, String subject) throws EmailSendingException;

    String generateOrderEmail(OrderDTO order) throws IOException, TemplateException;

    String generatePaymentEmail(PaymentDTO payment, User user) throws TemplateException, IOException;
}
