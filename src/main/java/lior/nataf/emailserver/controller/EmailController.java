package lior.nataf.emailserver.controller;

import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@Slf4j
@RestController
public class EmailController {

    private final JmsTemplate jmsTemplate;

    public EmailController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Email> sendEmail(@RequestBody Email email) {
        try {
            log.info("Received email:\n{}\nsending to work queue", email);
            jmsTemplate.convertAndSend("local.inmemory.queue", email);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
