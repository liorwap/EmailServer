package lior.nataf.emailserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource(value={"classpath:email.provider.properties"})
public class GmailEmailProviderConfig {

    @Value("${gmail.address}")
    private String mailServerHost;

    @Value("${gmail.username}")
    private String mailServerUsername;

    @Value("${gmail.password}")
    private String mailServerPassword;

    @Value("${gmail.postfix}")
    private String mailServerPostfix;

    @Bean
    public JavaMailSender getJavaEmailGmailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServerHost);
        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);
        return mailSender;
    }

    @Bean
    public String getGmailProviderPostfix(){
        return mailServerPostfix;
    }
}
