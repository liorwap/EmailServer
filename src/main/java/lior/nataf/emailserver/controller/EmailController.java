package lior.nataf.emailserver.controller;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.model.Email;
import lior.nataf.emailserver.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class EmailController {

    private final JmsTemplate jmsTemplate;
    private final EmailValidator emailValidator;

    public EmailController(JmsTemplate jmsTemplate, EmailValidator emailValidator) {
        this.jmsTemplate = jmsTemplate;
        this.emailValidator = emailValidator;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Email> sendEmail(@RequestBody Email email) {
        try {
            log.info("received email: {} validating", email);
            emailValidator.validate(email);
            log.info("Received email: {} sending to work queue", email);
            jmsTemplate.convertAndSend("local.inmemory.queue", email);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (InvalidEmailException invalidEmailException) {
            log.error("invalid email sent to server {}, error {}", email, invalidEmailException.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
