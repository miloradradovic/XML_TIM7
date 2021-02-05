package com.project.email.service;

import com.project.email.model.Tbody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

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

        ByteArrayDataSource byteArrayDataSourcePdf = new ByteArrayDataSource((byte[]) email.getFilePdf(), "application/pdf");
        helper.addAttachment(email.getFilePdfName(), byteArrayDataSourcePdf);

        ByteArrayDataSource byteArrayDataSourceHtml = new ByteArrayDataSource((byte[]) email.getFileHtml(), "text/html");
        helper.addAttachment(email.getFileHtmlName(), byteArrayDataSourceHtml);
        javaMailSender.send(mimeMessage);
    }

}

