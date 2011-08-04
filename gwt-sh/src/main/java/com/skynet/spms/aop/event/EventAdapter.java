package com.skynet.spms.aop.event;

import java.lang.reflect.Method;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.AnnoUtils;
import com.skynet.spms.event.EventEntry;
import com.skynet.spms.event.MsgPropName;
import com.skynet.spms.event.SpmsEventType;


@Component
public class EventAdapter {
	
	private Logger log=LoggerFactory.getLogger(EventAdapter.class);

	@Autowired
	@Qualifier("topicMsgTemplate")
	private JmsTemplate jmsTemplate;

	@AfterReturning(pointcut="execution(* com.skynet.spms..*.*(..)) "
			+ "&& @annotation(com.skynet.spms.aop.event.EventBind)",
			returning="result")			
	public void onVerifyAction(JoinPoint jp,Object result) {

		
		Method method = AnnoUtils.getAnno(jp, EventBind.class);

		EventBind event=method.getAnnotation(EventBind.class);
		
		log.debug("cutpoint active,event anno:"+event.log()+" name:"+event.name());
				
		if(result==null){
			return;
		}	
	
		if (result instanceof EventEntry) {
			fireForEvent((EventEntry)result, event);
		} 

	}

	@AfterThrowing(pointcut="execution(* com.skynet.spms..*.*(..)) "
		+ "&& @annotation(com.skynet.spms.aop.event.EventBind)",
		throwing="ex")			
	public void onException(JoinPoint jp,Throwable ex) {

		log.debug("cutpoint active on exception,exception:"+ex);

		Method method = AnnoUtils.getAnno(jp, EventBind.class);
		
		EventBind event=method.getAnnotation(EventBind.class);

		fireForCommException(ex, event);		

	}

	private void fireForEvent(EventEntry  fail, final EventBind event) {
		jmsTemplate.convertAndSend(fail,
				new MessagePostProcessor() {

					@Override
					public Message postProcessMessage(Message message)
							throws JMSException {
						message.setStringProperty(MsgPropName.TYPE, event
								.type().name());
						message.setStringProperty(MsgPropName.NAME,
								event.name());
						if (StringUtils.isNotBlank(event.log())) {
							message.setStringProperty(MsgPropName.LOG,
									event.log());
						}
						if (StringUtils.isNotBlank(event.sendTo())) {
							message.setStringProperty(MsgPropName.SEND_TO,
									event.sendTo());
						}
						return message;
					}

				});
	}

	private void fireForCommException(Throwable e, final EventBind event) {
		
		jmsTemplate.convertAndSend(new ExcepEvent(e), new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message)
					throws JMSException {
				message.setStringProperty(MsgPropName.TYPE,
						SpmsEventType.UnknowException.name());
				message.setStringProperty(MsgPropName.NAME, event.name());
				if (StringUtils.isNotBlank(event.log())) {
					message.setStringProperty(MsgPropName.LOG, event.log());
				}
				if (StringUtils.isNotBlank(event.sendTo())) {
					message.setStringProperty(MsgPropName.SEND_TO, event.sendTo());
				}
				return message;
			}

		});
	}
	
	private static class ExcepEvent implements EventEntry{
		private String msg=null;
		
		public ExcepEvent(Throwable excep){
			if(excep.getMessage()!=null){
				msg="error:"+excep.getMessage();
			}else{
				msg="error:exception "+excep.getClass().getName();
			}			
		}
		
		public String toString(){
			return msg;
		}
	}

}
