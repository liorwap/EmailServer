package lior.nataf.emailserver.unit.controller;
import lior.nataf.emailserver.controller.EmailController;
import lior.nataf.emailserver.model.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class EmailControllerTest {

    @Autowired
    private EmailController emailController;

    @Test
    public void sendValidEmail() {
        Email validEmail = new Email("lior@gmail.com", "lior@gmail.com", "some plaintext message");
        ResponseEntity<Email> result;
        String[] validEmailSenders = {"lior@gmail.com", "lior@walla.co.il", "lior@yahoo.com"};
        for (String sender : validEmailSenders) {
            validEmail.setFrom(sender);
            result = emailController.sendEmail(validEmail);
            Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        }
    }

    @Test
    public void sendInvalidEmail() {
        Email invalidEmail = new Email("lior@gmail.com", "lior@fake.com", "some plaintext message");
        ResponseEntity<Email> result = emailController.sendEmail(invalidEmail);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
