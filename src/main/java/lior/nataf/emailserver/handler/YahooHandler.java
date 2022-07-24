package lior.nataf.emailserver.handler;

import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class YahooHandler extends EmailHandler {

    public YahooHandler(@Qualifier("getJavaEmailYahooSender") JavaMailSender javaMailSender,
                        @Qualifier("getYahooProviderPostfix") String yahooProviderPostfix) {
        this.javaMailSender = javaMailSender;
        this.providerPostfix = yahooProviderPostfix;
    }
    @Override
    public void sendEmail(Email email) {
        super.sendEmail(email);
        log.info("Sent email from Yahoo provider {}", email);
    }
}
