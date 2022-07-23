package lior.nataf.emailserver.handler;

import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Slf4j
public abstract class EmailHandler {

    JavaMailSender javaMailSender;
    String providerPostfix;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setText(email.getBody());

//      //THIS IS JUST FOR DEBUG SIMULATE TIME FOR SENDING REAL EMAIL
//        try {
//            Thread.sleep(2 * 1000);
//        } catch (InterruptedException ie) {
//            Thread.currentThread().interrupt();
//        }
        try {
            javaMailSender.send(message);
            log.info("sent message {} to provider {}", message, providerPostfix);
        } catch (MailException me) {
            log.error("couldn't send email {}, mailException {}", email, me.getStackTrace());
        }
    }
    public String getPostfix() {
        return providerPostfix;
    }
}
