package com.skynet.common.network;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * javamail辅助类
 * @author jiang
 *
 */
@Component
public class MailTool {

	private Logger log = LoggerFactory.getLogger(MailTool.class);

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	private Properties props=new Properties();
	
	@Value("${mail.auth}")
	private String mailAuth;
	
	@PostConstruct
	public void init(){
		props.setProperty("mail.smtp.auth", mailAuth);		
	}

	/**
	 * 发送邮件，邮件发送人信息/接受人信息/邮件文本均通过message传入
	 * @param message
	 * @see com.skynet.common.network.MailMessage
	 */
	public void sendMail(final MailMessage message) {

		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(message.getFrom(), message
						.getPassword());
			}
		});
		
		mailSender.setSession(session);

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		
		try {
			
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

			message.fillMimeMsg(msgHelper);
			
			mimeMessage=msgHelper.getMimeMessage();

		} catch (MessagingException e) {
			// TODO:
			throw new IllegalArgumentException(e);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		mailSender.setPassword(message.getPassword());
		mailSender.send(mimeMessage);

	}

}
