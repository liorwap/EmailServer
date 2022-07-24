package lior.nataf.emailserver.service;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.exception.InvalidEmailProviderException;
import lior.nataf.emailserver.handler.*;
import lior.nataf.emailserver.model.Email;
import lior.nataf.emailserver.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{

    private final EmailHandlerContainer emailHandlerContainer;
    private final EmailValidator validator;

    public EmailServiceImpl(EmailHandlerContainer emailHandlerContainer, EmailValidator validator) {
        this.emailHandlerContainer = emailHandlerContainer;
        this.validator = validator;
    }

    @Override
    public void validate(Email email) throws InvalidEmailException {
        validator.validate(email);
    }

    @Override
    public void validateAndSend(Email email) {
        try {
            log.info("validating email before send {}", email);
            validate(email);
            String postfix = emailHandlerContainer.extractVendorPostfixFromAddress(email);
            EmailHandler emailHandler = getEmailHandler(postfix);
            log.info("Sending email {}", email);
            emailHandler.sendEmail(email);
        } catch (InvalidEmailException e) {
            log.error("Could not resolve email provider, not sending email {}.\n see Error {}",
                    email,
                    e.getStackTrace());
        }
    }

    private EmailHandler getEmailHandler(String postfix) throws InvalidEmailProviderException {
        EmailHandler emailHandler = emailHandlerContainer.getHandlerByProviderPostfix(postfix);
        if(emailHandler == null) {
            log.warn("Couldn't resolve provider Handler after email validation");
            throw new InvalidEmailProviderException(postfix);
        }
        return emailHandler;
    }
}
