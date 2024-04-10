package lab3;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class client {
    public static void main(String[] args) {

        String host = "smtp.gmail.com";
        String port = "587";
        String username = "";
        String password = "";

        String toAddress = "m";
        String subject = "Lab3";
        String bodyText = "Lab3 with image";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(bodyText);
            String imagePath = "src\\lab3\\egg.jpg";
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(imagePath);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(imagePart);
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Письмо успешно отправлено!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}

