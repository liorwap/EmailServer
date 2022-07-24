package lior.nataf.emailserver.handler;

import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GmailHandler extends EmailHandler {

    public GmailHandler(@Qualifier("getJavaEmailGmailSender") JavaMailSender javaMailSender,
                        @Qualifier("getGmailProviderPostfix") String gmailProviderPostfix) {
        this.javaMailSender = javaMailSender;
        this.providerPostfix = gmailProviderPostfix;
    }

    @Override
    public void sendEmail(Email email) {
        super.sendEmail(email);
        log.info("Sent email from Gmail provider {}", email);
    }
}
