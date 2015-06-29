package com.spanbee.check;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author sucheth.s
 * 
 */

public class EmailTest1 {

  public static void main(String[] args) {


    // TODO Auto-generated method stub
    EmailTest1 emailer = new EmailTest1();
    // the domains of these email addresses should be valid,
    // or the example will fail:
    emailer.sendEmail();
  }


  public void sendEmail() {
    Session mailSession = createSmtpSession();
    mailSession.setDebug(true);
    List<String> toAddresses = new ArrayList<String>();

    try {
      Transport transport = mailSession.getTransport();
      MimeMessage message = new MimeMessage(mailSession);

      message.setSubject("Check E-mail SPAN-BEE");
      message.setFrom(new InternetAddress("admin@shopus365.com"));
      message.setContent("<h1>Hello welcome to SPAN BEE</h1>", "text/html");

      toAddresses.add("sucheth13@gmail.com");
      toAddresses.add("sachin2sucheth@gmail.com");
      // toAddresses.add("nsanji@mscripts.com");
      // toAddresses.add("sbandi@mscripts.com");
      // toAddresses.add("rnarayanaswamy@mscripts.com");
      transport.connect();
      Iterator<String> itr = toAddresses.iterator();
      while (itr.hasNext()) {
        String toAddress = (String) itr.next();
        message.addRecipients(Message.RecipientType.TO, toAddress);


        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
      }


    } catch (MessagingException e) {
      System.err.println("Cannot Send email");
      e.printStackTrace();
    }
  }

  private Session createSmtpSession() {
    final Properties props = new Properties();
    props.setProperty("mail.host", "mail.shopus365.com");
    props.setProperty("mail.smtp.auth", "true");
    props.setProperty("mail.smtp.port", "" + 587);
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.transport.protocol", "smtp");
    // props.setProperty("mail.debug", "true");

    return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("admin@shopus365.com", "Ashraya@13");
      }
    });
  }



}
