package audit.part;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by jz36 on 25.05.16.
 */
public class Mail {
    private String login, pwd;
    private Authenticator auth;
    private StringTokenizer authLoginpwd;
    private Properties props;
    private Session session;
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public Mail(){
        login = "neo@biksileev.ru";
        pwd = "rjcjq12utybq";
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, pwd);
            }
        };
        props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.yandex.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        session = Session.getDefaultInstance(props, auth);



    }

    public void sendMail(String mailTo){
        try {
            MimeMessage message = new MimeMessage(session); // email message

            message.setFrom(new InternetAddress("neo@biksileev.ru")); // setting header fields

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

            message.setSubject("Test Mail from Java Program"); // subject line

            // actual mail body
            message.setText("You can send mail from Java program by using mail API, but you need" +
                    "couple of more JAR files e.g. smtp.jar and activation.jar");
            // Send message
            Transport.send(message);
            View.setCurrentOperation("Succesfuly send to " + mailTo);

        } catch (MessagingException mex){ mex.printStackTrace(); }
    }
}
