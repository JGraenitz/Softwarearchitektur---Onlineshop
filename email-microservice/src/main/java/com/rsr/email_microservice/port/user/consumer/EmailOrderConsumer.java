package com.rsr.email_microservice.port.user.consumer;

import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.core.domain.service.interfaces.IEmailService;
import com.rsr.email_microservice.core.domain.service.interfaces.IUserService;
import com.rsr.email_microservice.port.user.dto.OrderDTO;
import com.rsr.email_microservice.port.utils.EmailSendingException;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class EmailOrderConsumer {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IUserService userService;

    @RabbitListener(queues = {"${rabbitmq.email.order.queue.name}"})
    public void consume(OrderDTO order) {
        try {
            User user = new User(order.getUserId(), order.getFirstName(), order.getLastName(), order.getEmailAddress());
            String orderEmailContent = emailService.generateOrderEmail(order);
            emailService.sendEmail(order.getEmailAddress(), orderEmailContent, "Your Rock Solid Order was received");
            userService.saveUser(user);
            log.info("Order recieved and Email sent to " + order.getEmailAddress());
        } catch (ListenerExecutionFailedException listenerExecutionFailedException) {
            log.error(listenerExecutionFailedException.getMessage());
        } catch (IOException e) {
            log.error("Could not generate Order-Email");
        } catch (TemplateException e) {
            log.error("Could not generate Order-Email from specified Template");
        } catch (EmailSendingException e) {
            log.error("Could not send Email");
        }
    }

}
