package lior.nataf.emailserver.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class YahooHandler extends EmailHandler {

    public YahooHandler(@Qualifier("getJavaEmailYahooSender") JavaMailSender javaMailSender,
                        @Qualifier("getYahooProviderPostfix") String yahooProviderPostfix) {
        this.javaMailSender = javaMailSender;
        this.providerPostfix = yahooProviderPostfix;
    }
}
