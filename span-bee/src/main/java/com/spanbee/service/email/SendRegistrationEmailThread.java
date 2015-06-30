package com.spanbee.service.email;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import org.apache.log4j.Logger;

import com.spanbee.model.EmailModel;

/**
 * @author sucheth.s
 *
 */

public class SendRegistrationEmailThread implements Runnable{
  private EmailModel emailModel;
  
  public SendRegistrationEmailThread(EmailModel emailModel)
  {
    this.emailModel = emailModel;
  }

  private static final Logger LOGGER = Logger.getLogger(SendRegistrationEmailThread.class);
  
  @Override
  public void run() {
    LOGGER.info("***********Inside send e-Mail Thread************");
    LOGGER.info("Sending email to:: E-mail Address::" +emailModel.getToaddess());
    Session mailSession = createSmtpSession();
    mailSession.setDebug(true);
    List<String> toAddresses = new ArrayList<String>();

    try {
      Transport transport = mailSession.getTransport();
      MimeMessage message = new MimeMessage(mailSession);

       message.setSubject(emailModel.getSubject());
      message.setFrom(new InternetAddress(emailModel.getFromAddress()));
      message.setContent(emailModel.getContent(),"text/html");

      toAddresses.add(emailModel.getToaddess());
      transport.connect();
      Iterator<String> itr = toAddresses.iterator();
      while (itr.hasNext()) {
        String toAddress = (String) itr.next();
        message.addRecipients(Message.RecipientType.TO, toAddress);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
      }
      LOGGER.info("Successfully sent email to:: E-mail Address::" +emailModel.getToaddess());
    } catch (MessagingException e) {
      LOGGER.error("Cannot Send email",e);
    }
  }
  
  private Session createSmtpSession() {
    final Properties props = new Properties();
    props.setProperty("mail.host", emailModel.getHostName());
    props.setProperty("mail.smtp.auth", "true");
    props.setProperty("mail.smtp.port", "" + emailModel.getPort());
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.transport.protocol", emailModel.getProtocol());
    // props.setProperty("mail.debug", "true");

    return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(emailModel.getUserName(), emailModel.getPassword());
      }
    });
  }

}

