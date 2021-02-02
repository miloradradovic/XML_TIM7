package com.project.email.service;

import com.project.email.model.Tbody;
import com.sun.istack.internal.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Async
	public void sendPlainMail(Tbody email) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(email.getTo());
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject(email.getSubject());
		helper.setText(email.getContent(), true);
		javaMailSender.send(mimeMessage);
	}

	@Async
	public void sendAttachMail(Tbody email) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");
		helper.setTo(email.getTo());
		helper.setFrom(env.getProperty("spring.mail.username"));
		helper.setSubject(email.getSubject());
		helper.setText(email.getContent(), true);

		//String pdfName = email.getFile().split("\\|")[0];
		//String pdf = email.getFile().split("\\|")[1];
		Charset charset = StandardCharsets.UTF_8;

		ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(email.getFile().getBytes(charset), "application/pdf");
		helper.addAttachment("name.pdf" , byteArrayDataSource);
		javaMailSender.send(mimeMessage);
	}

}

