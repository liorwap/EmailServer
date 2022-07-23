package lior.nataf.emailserver.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailHandler extends EmailHandler {

    public GmailHandler(@Qualifier("getJavaEmailGmailSender") JavaMailSender javaMailSender,
                        @Qualifier("getGmailProviderPostfix") String gmailProviderPostfix) {
        this.javaMailSender = javaMailSender;
        this.providerPostfix = gmailProviderPostfix;
    }
}
