package com.skynet.common.aop.message;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class MessageRegisterAdapte {
	
	private JmsTemplate jmsTemplate;
	
	private DefaultMessageListenerContainer defaultContainer;
	
	
	public void register(final MessageListener listener,final String selection) throws JMSException{
		
		jmsTemplate.execute(new SessionCallback<Boolean>(){

			@Override
			public Boolean doInJms(Session session) throws JMSException {
				
				MessageConsumer consumer=session.createConsumer(
						jmsTemplate.getDefaultDestination(), selection);
				consumer.setMessageListener(listener);
				
				return true;
			}
			
		});	
		
	}
	

}
