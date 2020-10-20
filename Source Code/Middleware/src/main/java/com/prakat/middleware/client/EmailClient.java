package com.prakat.middleware.client;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.prakat.middleware.entity.User;
import com.prakat.middleware.exception.BussinessException;

@Component
public class EmailClient {
	final String username = "rhss@prakat.in";
	final String password = "Prakat123";
	Properties props = new Properties();
	Session session;
	public EmailClient() {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.prakat.in");
		props.put("mail.smtp.port", "25");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}
	public void sendEmail(User user, Integer otp) throws BussinessException {
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
		String userName = user.getName();
		String email = user.getEmail();
		
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		mimeMessage.setFrom(new InternetAddress("rhss@prakat.in"));
		mimeMessage.setSubject("OTP for Diciccos Application login");
		MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
		mimeBodyPart1.setContent("<h2>Hi, "+userName+" !</h2><h3>OTP: " 
				+ otp+"</h3>\n"
				+ "Do not share this OTP with anyone." + "<br /><br />\n" + "\n" + "\n"
				+ "Thank You," + "<br />\n" + "\n" + "Diciccos Team","text/html");
		
		// create the Multipart and add parts to it
		Multipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(mimeBodyPart1);
		mimeMessage.setContent(multiPart);
		Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException("Error occured while sending Email");
		}
		
	}
}
