package fit.iuh.week_5.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private static final String OWNER_EMAIL = "longnguyendev2020@gmail.com";
  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail, String body, String subject) {

    MimeMessage message = mailSender.createMimeMessage();
    try {
      // Use MimeMessageHelper to set the content of the message as HTML
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(OWNER_EMAIL);
      helper.setTo(toEmail);
      helper.setSubject(subject);
      helper.setText(body, true); // Set the second parameter to true to indicate HTML content
      mailSender.send(message);
      System.out.println("Email sent successfully");
    } catch (MessagingException e) {
      System.out.println("Failed to send email: " + e.getMessage());
    }
  }
}
