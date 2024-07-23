package africa.semicolon.note.services;

import africa.semicolon.note.data.models.User;
import africa.semicolon.note.data.models.VerificationToken;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final VerificationServiceImplementation verificationServiceImplementation;
    @Autowired
    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, VerificationServiceImplementation verificationServiceImplementation) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.verificationServiceImplementation = verificationServiceImplementation;
    }
   public void sendHtmlMail(User user) throws MessagingException {
       VerificationToken verificationToken = verificationServiceImplementation.findByUser(user);
       if(verificationToken != null){
           String token = verificationToken.getToken();
           Context context = new Context();
           context.setVariable("title","verify your email address");
           context.setVariable("link","http://localhost:8084/activation?token -" + token);
            String body = templateEngine.process("verification",context);

           MimeMessage message = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message,true);
           helper.setTo(user.getEmail());
           helper.setSubject("email address verification");
           helper.setText(body,true);
           javaMailSender.send(message);
       }
   }
}
