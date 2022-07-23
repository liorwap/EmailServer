package lior.nataf.emailserver.service;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.exception.InvalidEmailProviderException;
import lior.nataf.emailserver.handler.EmailHandler;
import lior.nataf.emailserver.handler.GmailHandler;
import lior.nataf.emailserver.handler.WallaHandler;
import lior.nataf.emailserver.handler.YahooHandler;
import lior.nataf.emailserver.model.Email;
import lior.nataf.emailserver.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
    private final GmailHandler gmailHandler;

    private final WallaHandler wallaHandler;

    private final YahooHandler yahooHandler;

    private final EmailValidator validator;

    public EmailServiceImpl(GmailHandler gmailHandler, WallaHandler wallaHandler, YahooHandler yahooHandler, EmailValidator validator) {
        this.gmailHandler = gmailHandler;
        this.wallaHandler = wallaHandler;
        this.yahooHandler = yahooHandler;
        this.validator = validator;
    }

    @Override
    public void validate(Email email) throws InvalidEmailException {
        validator.validate(email);
    }

    @Override
    public void validateAndSend(Email email) {
        try {
            log.info("validating email {} before send", email);
            validate(email);
            String postfix = "@" + email.getFrom().split("@")[1];
            EmailHandler emailHandler;
            emailHandler = getEmailHandler(postfix);
            log.info("sending email {} using provider {}", email, postfix);
            emailHandler.sendEmail(email);
        } catch (InvalidEmailException e) {
            log.error("Could not resolve email provider, not sending email {}.\n see Error {}",
                    email,
                    e.getStackTrace());
        }
    }

    private EmailHandler getEmailHandler(String postfix) throws InvalidEmailProviderException {
        if(postfix.equals(gmailHandler.getPostfix())) {
            return gmailHandler;
        } else if(postfix.equals(wallaHandler.getPostfix())) {
            return wallaHandler;
        } else if(postfix.equals(yahooHandler.getPostfix())) {
            return yahooHandler;
        } else {
            log.warn("Couldn't resolve provider Handler after email validation");
            throw new InvalidEmailProviderException(postfix);
        }
    }
}
