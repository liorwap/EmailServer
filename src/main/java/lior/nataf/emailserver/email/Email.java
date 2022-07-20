package lior.nataf.emailserver.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Email {
    private final String to;
    private final String from;
    private final String body;
}
