package lior.nataf.emailserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource(value={"classpath:email.provider.properties"})
public class YahooEmailProviderConfig {
    @Value("${yahoo.address}")
    private String mailServerHost;

    @Value("${yahoo.username}")
    private String mailServerUsername;

    @Value("${yahoo.password}")
    private String mailServerPassword;

    @Value("${yahoo.postfix}")
    private String mailServerPostfix;

    @Bean
    public JavaMailSender getJavaEmailYahooSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServerHost);
        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);
        return mailSender;
    }

    @Bean
    public String getYahooProviderPostfix(){
        return mailServerPostfix;
    }

}
