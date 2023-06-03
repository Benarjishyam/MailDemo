import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

    public static void main(String[] args) throws AddressException, MessagingException {

        // Get the recipient email addresses
        String[] recipientEmails = { "your@gmail.com"};  // we can put multiple here
        String messageContent = "This is an important message.";
        String tableContent = "<table>"
        		+ "    <thead>\r\n      <tr>\r\n        <th>Column 1</th>\r\n        <th>Column 2</th>\r\n      </tr>\r\n    </thead>" +
               "    <tbody>\r\n      <tr>\r\n        <td>Row 1, Cell 1</td>\r\n        <td>Row 1, Cell 2</td>\r\n      </tr>\r\n      <tr>\r\n        <td>Row 2, Cell 1</td>\r\n        <td>Row 2, Cell 2</td>\r\n      </tr>\r\n      <tr>\r\n        <td>Row 3, Cell 1</td>\r\n        <td>Row 3, Cell 2</td>\r\n      </tr>\r\n    </tbody>"+
                "</table>";

        // Create a new MimeMessage object
        MimeMessage message = new MimeMessage(getSession());

        // Set the sender email address
        message.setFrom(new InternetAddress("to@gmail.com"));

        // Set the recipient email addresses
        for (String recipientEmail : recipientEmails) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        }

        // Set the subject of the email
        message.setSubject("Alert Email");

        // Set the body of the email
        message.setText("This is an alert email. The current time is " + new Date());
        
        // Set the email content
       // message.setContent(messageContent, "text/plain");
        // Create the HTML content
        String htmlContent = "<html><head>\r\n  <title>Table Example</title>\r\n  <style>\r\n    table {\r\n      border-collapse: collapse;\r\n      width: 100%;\r\n    }\r\n    \r\n    th, td {\r\n      border: 1px solid black;\r\n      padding: 8px;\r\n      text-align: left;\r\n    }\r\n  </style>\r\n</head><body>" + tableContent + "</body></html>";

        message.setContent(htmlContent, "text/html");

        // Set the high importance flag
        // Set additional headers
        message.setHeader("X-Priority", "1");
        message.setHeader("X-Mailer", "JavaMail API");
        message.setHeader("Importance", "high");
        
        // Send the email
        try {
            Transport.send(message);
            System.out.println("Mail SENT");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("to@gmail.com", "drramqhgcyqeqjtf");
            }
        });

        return session;
    }
}