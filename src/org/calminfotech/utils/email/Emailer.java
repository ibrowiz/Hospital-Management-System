package org.calminfotech.utils.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.calminfotech.utils.AppConfig;

public class Emailer {

	private static String host = AppConfig.Mail_IP;
	private static String port = AppConfig.MAIL_PORT;
	private static String from = AppConfig.DEFAULT_EMAIL_SENDER;

	public static boolean send(String to, String subject, String body) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");// false
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Session
		Session session = Session.getDefaultInstance(props);
		try {

			Message email = new MimeMessage(session);
			email.setFrom(new InternetAddress(from));
			email.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			email.setSubject(subject);
			// Send the actual HTML message, as big as you like
			email.setContent(body, "text/html");

			Transport.send(email);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String [] args){
		send("ntkyari@gmail.com", "Sample Main", "I am a test mail");
		System.out.println("--- END");
	}
	

}
