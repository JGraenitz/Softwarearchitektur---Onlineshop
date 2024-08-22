package com.rsr.email_microservice.port.admin.controller;


import com.rsr.email_microservice.core.domain.service.impl.EmailService;
import com.rsr.email_microservice.port.admin.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto.getEmailAddress(), emailDto.getContent(), emailDto.getSubject());
        return ResponseEntity.ok("Email sent successfully!");
    }
}
