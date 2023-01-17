package africa.unicoin.unicoin.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void send(String to, String email) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper  mimeMessageHelper = new MimeMessageHelper(mailMessage, mailMessage.getEncoding());
        mimeMessageHelper.setSubject("Confirmed your email");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("lekan.sofuyi@gmail.com");
        mimeMessageHelper.setText(email, true);
        mailSender.send(mailMessage);
    }
}
