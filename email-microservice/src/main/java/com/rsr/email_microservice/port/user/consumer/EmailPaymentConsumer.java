package com.rsr.email_microservice.port.user.consumer;


import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.core.domain.service.interfaces.IEmailService;
import com.rsr.email_microservice.core.domain.service.interfaces.IUserService;
import com.rsr.email_microservice.port.user.dto.PaymentDTO;
import com.rsr.email_microservice.port.utils.EmailSendingException;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class EmailPaymentConsumer {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IUserService userService;

    @RabbitListener(queues = {"${rabbitmq.email.payment.queue.name}"})
    public void consume(PaymentDTO payment) {
        try {
            User user = userService.getUserById(payment.getUserId());
            String paymentEmailContent = emailService.generatePaymentEmail(payment, user);
            emailService.sendEmail(user.getEmailAddress(), paymentEmailContent, "Your Rock Solid Order has been paid!");
            log.info("Payment recieved and Email sent to " + user.getEmailAddress());
        } catch (TemplateException e) {
            log.info("Payment-Email could not be generated from Template.");
        } catch (IOException e) {
            log.info("Payment-Email-Template could not be loaded.");
        } catch (EmailSendingException e) {
            log.info("Could not send Payment-Email");
        }
    }
}
