package com.test.testpro.Controller;

import com.test.testpro.model.EmailEntity;
import com.test.testpro.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailEntityController {
    private EmailService emailService;

    public EmailEntityController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmail")
    public String
    sendMail(@RequestBody EmailEntity email) {
        String resp = emailService.sendSimpleMail(email);
        return resp;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailEntity email) {
        String resp = emailService.sendMailWithAttachment(email);
        return resp;
    }
}
