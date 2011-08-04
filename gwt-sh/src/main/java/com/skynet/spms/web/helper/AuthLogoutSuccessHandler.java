package com.skynet.spms.web.helper;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.AuthHelper;
import com.skynet.spms.event.MsgPropName;
import com.skynet.spms.event.SpmsEventType;


@Component("logoutHandler")
public class AuthLogoutSuccessHandler implements LogoutSuccessHandler{
	
	private Logger log=LoggerFactory.getLogger(AuthLogoutSuccessHandler.class);
	
	
	@Autowired
	@Qualifier("topicMsgTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		
		log.info("now ,user logout");
		
		final String userName=AuthHelper.getUserByAuth(authentication);
		
		
		jmsTemplate.send(new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message=session.createTextMessage(userName);
				message.setStringProperty(MsgPropName.TYPE, SpmsEventType.UserLogout.name());
				message.setStringProperty(MsgPropName.LOG, "info");
				return message;
			}
			
		});
		
		response.sendRedirect("./login.html");
	}

}
