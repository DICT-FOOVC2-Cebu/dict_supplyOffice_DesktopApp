/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;

/**
 *
 * @author Eunar B. Dayangco
 */
public class SendEmail {
    
    private String selfEmail;
    private String selfPassword;

    public SendEmail() {
        
       //  this.selfEmmail="supplyofficeDICT@gmail.com";
       // this.selfPassword = "MadeByOJT_4_SupplyOffice_2018";
       this.selfEmail = "supplyofficeDICT@gmail.com";
       this.selfPassword = "MadeByOJT_4_SupplyOffice_2018";
    }

    public String getSelfEmmail() {
        return selfEmail;
    }

    public void setSelfEmmail(String selfEmmail) {
        this.selfEmail = selfEmmail;
    }

    public String getSelfPassword() {
        return selfPassword;
    }

    public void setSelfPassword(String selfPassword) {
        this.selfPassword = selfPassword;
    }
    
    
    
    public boolean sendMail(String ToEmail,String Subject,String Text){

    
        final String username = this.getSelfEmmail();
        final String password = this.getSelfPassword();

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
            
            
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getSelfEmmail()));//ur email
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(ToEmail));//u will send to
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);

            return true;
            

        } catch (Exception e) {
  
            CreateSection.displayInfoMessage("Mailed Error", e.toString());
            return false;
        }
        
    }
    
    public boolean sendMailWithAttachedMent(String ToEmail,String Subject,String Text){

	JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        String myPath = chooser.getSelectedFile().getPath();

        //CreateSection.displayInfoMessage("Email Attached Path", myPath);
    
        final String username = this.getSelfEmmail();
        final String password = this.getSelfPassword();

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
            
            
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getSelfEmmail()));//ur email
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(ToEmail));//u will send to
            message.setSubject(Subject);
            Multipart multipart = new MimeMultipart();
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(Text);
            multipart.addBodyPart(messageBodyPart);
            
            
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(myPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("users.xls");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
          
            return true;

        } catch (Exception e) {
        
            CreateSection.displayInfoMessage("Mailed Error", e.toString());
            return false;
        }
        
    }
    
    
}
