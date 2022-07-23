package lior.nataf.emailserver.service;

import lior.nataf.emailserver.exception.InvalidEmailException;
import lior.nataf.emailserver.model.Email;

public interface EmailService {

    void validate(Email email) throws InvalidEmailException;

    void validateAndSend(Email email);
}
