package com.spanbee.check;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author sucheth.s
 *
 */

public class EmailTest {
  
  public static void main(String[] args) {
    
    
   

    String to = "sucheth13@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "admin@shopus365.com";

    // Assuming you are sending email from localhost
    String host = "mail.shopus365.com";
   
    // Get system properties
    Properties properties = System.getProperties();
//System.out.println(properties.getProperty("mail.smtp.localhost"));
    // Setup mail server
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", "587");
    properties.setProperty("mail.user", "admin@shopus365.com");
    properties.setProperty("mail.password", "Ashraya@13");
   properties.put("mail.smtp.localhost","MSI-LTP-014.msiblr.com");
   
    // Get the default Session object.
    Session session = Session.getDefaultInstance(properties);

    try{
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(from));

       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));

       // Set Subject: header field
       message.setSubject("This is the Subject Line!");

       // Now set the actual message
       message.setText("This is actual message");

       // Send message
       Transport.send(message);
       System.out.println("Sent message successfully....");
    }catch (MessagingException mex) {
       mex.printStackTrace();
    }
 }
  }



