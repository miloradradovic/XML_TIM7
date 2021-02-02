package com.project.email.service;

import com.project.email.model.Email;
import com.project.email.model.Tbody;
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
	public void sendMail(Tbody email) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(email.getTo());
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject(email.getSubject());
		helper.setText(email.getContent(), true);
		javaMailSender.send(mimeMessage);
	}

}

