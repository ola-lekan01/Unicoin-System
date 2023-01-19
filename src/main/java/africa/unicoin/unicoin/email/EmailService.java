package africa.unicoin.unicoin.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    @Async
    public void send(String to, String email) throws MessagingException {
        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper  mimeMessageHelper = new MimeMessageHelper(mailMessage, mailMessage.getEncoding());
            mimeMessageHelper.setSubject("Confirmed your email");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("lekan.sofuyi@gmail.com");
            mimeMessageHelper.setText(email, true);
            mailSender.send(mailMessage);

        } catch (MessagingException e) {
            log.info("problem1: ");
            log.info(e.getMessage());
        } catch (MailException e) {
            log.info("problem2: ");
            log.info(e.getMessage());
        }
    }

//    @Scheduled(cron = "* * * * *")
//    public void resendMessage() throws MessagingException {
//        send(sendTo, sentFrom);
//    }
}
