package lior.nataf.emailserver.validator;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.exception.InvalidEmailProviderException;
import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
@PropertySource(value={"classpath:email.provider.properties"})
public class EmailValidator {
    @Value("${gmail.postfix}")
    private String gmailMailServerPostfix;
    @Value("${walla.postfix}")
    private String wallaMailServerPostfix;
    @Value("${yahoo.postfix}")
    private String yahooMailServerPostfix;


    public void validate(Email email) throws InvalidEmailException {
        Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");
        if(!emailPattern.matcher(email.getFrom()).matches()) {
            log.error("Invalid email from {}", email.getFrom());
            throw new InvalidEmailException(email.getFrom());
        }
        if(!emailPattern.matcher(email.getTo()).matches()) {
            log.error("Invalid email to {}", email.getTo());
            throw new InvalidEmailException(email.getTo());
        }
        String postfix = "@" + email.getFrom().split("@")[1];
        boolean isSenderOneOfSupportedProviders = postfix.equals(gmailMailServerPostfix);
        isSenderOneOfSupportedProviders |= postfix.equals(wallaMailServerPostfix);
        isSenderOneOfSupportedProviders |= postfix.equals(yahooMailServerPostfix);
        if(!isSenderOneOfSupportedProviders) {
            log.error("Invalid provider from {}", email);
            throw new InvalidEmailProviderException(email.getFrom());
        }
    }
}
