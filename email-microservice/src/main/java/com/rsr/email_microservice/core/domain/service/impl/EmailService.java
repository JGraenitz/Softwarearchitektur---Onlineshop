package com.rsr.email_microservice.core.domain.service.impl;


import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.core.domain.service.interfaces.IEmailService;
import com.rsr.email_microservice.core.domain.service.utils.EmailTemplateGenerator;
import com.rsr.email_microservice.port.user.dto.OrderDTO;
import com.rsr.email_microservice.port.user.dto.PaymentDTO;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailId;

    private static final EmailTemplateGenerator emailTemplateGenerator = new EmailTemplateGenerator();

    @Override
    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailId);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public String generateOrderEmail(OrderDTO order) throws TemplateException, IOException {

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", order.getFirstName() + " " + order.getLastName());
        dataModel.put("orderNumber", order.getOrderId());
        dataModel.put("date", order.getOrderCreationTime());
        dataModel.put("items", order.getItems().stream()
                .map(productDTO -> productDTO.getProductName() + " (" + productDTO.getPriceInEuro() + "€)  x" +
                        productDTO.getAmount()).toList());

        return emailTemplateGenerator.generateEmail("email-order-template.ftl", dataModel);
    }

    @Override
    public String generatePaymentEmail(PaymentDTO payment, User user) throws TemplateException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", user.getFirstName() + " " + user.getLastName());
        dataModel.put("orderNumber", payment.getOrderId());
        dataModel.put("amountInEuro", payment.getAmountInEuro());

        return emailTemplateGenerator.generateEmail("email-payment-template.ftl", dataModel);
    }
}
