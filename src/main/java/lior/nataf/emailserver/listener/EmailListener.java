package lior.nataf.emailserver.listener;

import lior.nataf.emailserver.model.Email;
import lior.nataf.emailserver.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailListener {

    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = "local.inmemory.queue", concurrency = "10")
    public void receiveEmail(Email email) {
        log.info("Email: {} received from work queue sending to provider handler", email);

        emailService.validateAndSend(email);

    }
}
