package lior.nataf.emailserver.validator;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.exception.InvalidEmailProviderException;
import lior.nataf.emailserver.handler.EmailHandlerContainer;
import lior.nataf.emailserver.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
@PropertySource(value={"classpath:email.provider.properties"})
public class EmailValidator {

    private final EmailHandlerContainer emailHandlerContainer;

    public EmailValidator(EmailHandlerContainer emailHandlerContainer) {
        this.emailHandlerContainer = emailHandlerContainer;
    }

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
        String postfix = emailHandlerContainer.extractVendorPostfixFromAddress(email);
        if(!emailHandlerContainer.contains(postfix)) {
            log.error("Invalid provider from {}", email);
            throw new InvalidEmailProviderException(email.getFrom());
        }
    }
}
