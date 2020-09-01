package com.indra.icertify.api.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	private static final Logger log = LoggerFactory.getLogger(SendMail.class);

	public Boolean invioMail(String toEmail, String token) {

		log.info("**Before SendMAil - invioMAil**");

		Boolean response = false;

		String to = toEmail;
		//Inserire mail e password Hotmail, se si usa un altro ricavare lo smtp.host e porta
		String from = "";
		String password = "";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Icertify Mail");
			message.setText("Per favore clicka qui per verificare la mail " + token);
 
			Transport.send(message);

			log.info("**After SendMAil - invioMAil**");
			response = true;

		} catch (Exception e) {
			log.info("**Error SendMAil - invioMAil** " + e);
		}
		return response;
	}
}
