package com.project.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Date;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Async
	public void sendMail(String email){
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Email");
		mail.setText("Email text");
		javaMailSender.send(mail);
		
		/*MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(email);
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject("Email");
		helper.setSentDate(new Date());
		helper.setText("<h1>Email</h1>" + "\n\n" + "<p>Email text</p>", true);
		javaMailSender.send(mimeMessage);*/

		/*BodyPart messageBodyPart = new MimeBodyPart(); 
		messageBodyPart.setText("Mail Body");*/

	}

}

