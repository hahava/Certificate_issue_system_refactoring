package com.tomato.service;

import com.tomato.dto.EmailDTO;
import com.tomato.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 인증 정보를 요청자의 메일로 전송하는 기능
 *
 * TODO : 요청정보(졸업, 재학)별 인증 API를 작성해야함
 * @author kalin
 */
@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	private final String EMAIL_SENDER = "hahava@naver.com";

	public static final String TEMPLATE = "<section>\n" +
		"    <article style='float: left; padding: 20px; width: 40%; border: 0.5px solid black; height: 50%;'>\n" +
		"        <a href='http://www.sejong.ac.kr'>\n" +
		"            <img src='http://sejong.ac.kr/img/common/h1_top_logo01.gif' alt='Sejong' height='25%' width='25%'>\n" +
		"        </a>\n" +
		"        <h1>{NAME}</h1>\n" +
		"        <hr>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>전화 : </p>\n" +
		"        <p style='display:inline; font-size:12px'>02-3408-3114</p>\n" +
		"        <br>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>FAX : </p>\n" +
		"        <a style='background-color: #00B4DB; color: white; padding: 8px 25px; text-align: center; text-decoration: none;\n" +
		"            display: inline-block; float:right; font-weight:bold;'\n" +
		"            href='http://localhost:8080/certification.do/{TYPE}/'> 인증\n" +
		"        </a>\n" +
		"		<p>{RESULT}</p>	" +
		"		<p>{TIME}</p>" +
		"        <p style=' display:inline; font-size:12px'>02-6935-2419</p>\n" +
		"        <br>\n" +
		"        <p style='font-weight:bold; display:inline; font-size:12px'>E-mail : </p>\n" +
		"        <p style='display:inline; font-size:12px'>sw6@sejong.ac.kr</p>\n" +
		"    </article>\n" +
		"</section>";

	public void sendEmail(EmailDTO email, String certificationType)
		throws MailException, MessagingException {

		String emailText = TEMPLATE.replace("{NAME}", email.getUserId())
			.replace("{TYPE}", certificationType)
			.replace("{RESULT}", email.getCertificationResult())
			.replace("{TIME}", email.getRequestTime());

		MimeMessage msg = mailSender.createMimeMessage();
		msg.setSubject(email.getSubject(), StandardCharsets.UTF_8.toString());
		msg.setText(emailText, "utf-8", "html");
		msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
		msg.setFrom(EMAIL_SENDER);
		mailSender.send(msg);
	}
}