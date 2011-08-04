package com.skynet.spms.web.helper;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.AuthHelper;
import com.skynet.spms.event.MsgPropName;
import com.skynet.spms.event.SpmsEventType;

@Component
public class AuthSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

	private Logger log=LoggerFactory.getLogger(AuthSuccessEventHandler.class);
	
	@Autowired
	@Qualifier("topicMsgTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
					
		String userName=AuthHelper.getUserByAuth(event.getAuthentication());			
		
		jmsTemplate.convertAndSend((Object)userName,new MessagePostProcessor(){

			@Override
			public Message postProcessMessage(Message message)
					throws JMSException {
				message.setStringProperty(MsgPropName.TYPE, SpmsEventType.UserLogin.name());
				message.setStringProperty(MsgPropName.LOG, "info");
				return message;
			}
			
		} );
		
	}


}
