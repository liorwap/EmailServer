package lior.nataf.emailserver.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class WallaHandler extends EmailHandler {

    public WallaHandler(@Qualifier("getJavaEmailWallaSender") JavaMailSender javaMailSender,
                        @Qualifier("getWallaProviderPostfix") String wallaProviderPostfix) {
        this.javaMailSender = javaMailSender;
        this.providerPostfix = wallaProviderPostfix;
    }
}
