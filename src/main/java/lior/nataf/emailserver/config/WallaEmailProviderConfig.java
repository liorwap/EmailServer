package lior.nataf.emailserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource(value={"classpath:email.provider.properties"})
public class WallaEmailProviderConfig {
    @Value("${walla.address}")
    private String mailServerHost;

    @Value("${walla.username}")
    private String mailServerUsername;

    @Value("${walla.password}")
    private String mailServerPassword;

    @Value("${walla.postfix}")
    private String mailServerPostfix;

    @Bean
    public JavaMailSender getJavaEmailWallaSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServerHost);
        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);
        return mailSender;
    }

    @Bean
    public String getWallaProviderPostfix(){
        return mailServerPostfix;
    }
}
