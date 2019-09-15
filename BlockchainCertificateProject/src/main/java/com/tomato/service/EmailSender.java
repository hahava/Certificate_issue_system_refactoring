package com.tomato.service;

import com.tomato.dto.DiplomaDTO;
import com.tomato.dto.EmailDTO;
import com.tomato.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	public static final String TEMPLATE = "<section>\n" +
		"    <article style='float: left; padding: 20px; width: 40%; border: 0.5px solid black; height: 50%;'>\n" +
		"        <a href='http://www.sejong.ac.kr'>\n" +
		"            <img src='http://sejong.ac.kr/img/common/h1_top_logo01.gif' alt='Sejong' height='25%' width='25%'>\n" +
		"        </a>\n" +
		"        <h1> {NAME} / {MAJOR} </h1>\n" +
		"        <hr>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>전화 : </p>\n" +
		"        <p style='display:inline; font-size:12px'>02-3408-3114</p>\n" +
		"        <br>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>FAX : </p>\n" +
		"        <a style='background-color: #00B4DB; color: white; padding: 8px 25px; text-align: center; text-decoration: none;\n" +
		"            display: inline-block; float:right; font-weight:bold;'\n" +
		"            href='http://localhost:8080/blockchain/certification.do/diploma/{RESULT}/enrollment/null/time/{TIME}'> 인증\n" +
		"        </a>\n" +
		"        <p style=' display:inline; font-size:12px'>02-6935-2419</p>\n" +
		"        <br>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>E-mail : </p>\n" +
		"        <p style='display:inline; font-size:12px'>sw6@sejong.ac.kr</p>\n" +
		"    </article>\n" +
		"</section>";

	public void SendEmail(EmailDTO email) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			msg.setSubject(email.getSubject());
			msg.setText(TEMPLATE, "UTF-8", "html");
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReciver()));
			msg.setFrom("hahava@naver.com");
			mailSender.send(msg);
		} catch (MailException | MessagingException me) {
			me.printStackTrace();
		}
	}

}