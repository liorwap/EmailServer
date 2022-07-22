package lior.nataf.emailserver.listener;

import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailListener {

    @JmsListener(destination = "local.inmemory.queue")
    public void receiveEmail(Email email) {
        log.info("Email: {} received from work queue sending to provider handler", email);
    }
}
