package lior.nataf.emailserver.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sendEmail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @GetMapping
    public String sendEmail(Email email) {
        return emailService.sendEmail(email);
    }
}
