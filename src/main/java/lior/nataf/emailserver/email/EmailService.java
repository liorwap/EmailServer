package lior.nataf.emailserver.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public String sendEmail(Email email) {
        log.info("sending email :\n {}", email.toString());
        return "Sent Email";
    }
}
