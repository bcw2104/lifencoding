package com.lifencoding.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;


public class MailTools {
	@Value("${mail.title}")
	private String mailTitle;
	@Value("${mail.username}")
	private String sender;

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendAdminInfoMail(String to,String id,String pw) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();


		String subject = mailTitle+ " 계정 찾기";

		StringBuffer page = new StringBuffer();

		page.append("<html>");
		page.append("<body>");
		page.append("<div style='width: 500px; min-height: 300px; padding-left: 30px; margin-right: 80px; box-shadow: 1px 1px 3px 0 rgba(82, 62, 62, 0.4);"
				+ "font-family: Arial, Helvetica, sans-serif;'>");
		page.append("<div style='padding: 20px 0 10px 0; border-bottom: 5px solid #0059ab; font-weight: bold; font-size: 1.3rem; margin-bottom: 10px;'>"+mailTitle+"</div>");
		page.append("<h2>계정 찾기</h2>");
		page.append("<div style='padding: 10px 0; font-size:16px'>");
		page.append("아이디 : <span style='font-weight:bold;'>"+id+"</span><br/>");
		page.append("비밀번호 : <span style='font-weight:bold;'>"+pw+"</span><br/>");
		page.append("<div style='margin-top: 10px;font-size: 13px;'>※ 로그인 후 비밀번호를 반드시 변경하시길 바랍니다.</div>");
		page.append("</div>");
		page.append("</div>");
		page.append("</body>");
		page.append("</html>");

		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		messageHelper.setFrom(sender, mailTitle);
		messageHelper.setSubject(subject);
		messageHelper.setTo(to);
		messageHelper.setText(page.toString(), true);

		mailSender.send(message);
	}

	public void sendConfirmMail(String to,String code) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();


		String subject = mailTitle+ " 이메일 변경 인증메일";

		StringBuffer page = new StringBuffer();

		page.append("<html>");
		page.append("<body>");
		page.append("<div style='width: 500px; min-height: 300px; padding-left: 30px; margin-right: 80px; box-shadow: 1px 1px 3px 0 rgba(82, 62, 62, 0.4);"
				+ "font-family: Arial, Helvetica, sans-serif;'>");
		page.append("<div style='padding: 20px 0 10px 0; border-bottom: 5px solid #0059ab; font-weight: bold; font-size: 1.3rem; margin-bottom: 10px;'>"+mailTitle+"</div>");
		page.append("<h2>이메일 변경 코드</h2>");
		page.append("<div style='padding: 10px 0; font-size:16px'>");
		page.append("인증코드 : <span style='font-weight:bold;'>"+code+"</span><br/>");
		page.append("<div style='margin-top: 10px;font-size: 13px;'>※ 인증코드는 확인 후 폐기됩니다.</div>");
		page.append("</div>");
		page.append("</div>");
		page.append("</body>");
		page.append("</html>");

		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		messageHelper.setFrom(sender, mailTitle);
		messageHelper.setSubject(subject);
		messageHelper.setTo(to);
		messageHelper.setText(page.toString(), true);

		mailSender.send(message);
	}

}
