package lior.nataf.emailserver.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidEmailException extends Exception {
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
