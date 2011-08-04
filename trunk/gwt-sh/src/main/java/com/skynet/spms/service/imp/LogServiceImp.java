package com.skynet.spms.service.imp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.DailyRollingFileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.event.MsgPropName;
import com.skynet.spms.event.SpmsEventType;
import com.skynet.spms.service.LogService;

@Component("logService")
public class LogServiceImp implements LogService, MessageListener {

	private final Logger log = LoggerFactory.getLogger(LogServiceImp.class);

	private final org.apache.log4j.Logger fileLog =org.apache.log4j.Logger.getLogger("spms.businesslog");

//	private StringBuilder  strBuild;
//
//	private File file;
//	
//	private OutputStream output;
//	
	@Value("${log.dir.name}")
	private String baseDirName;
	
	@PostConstruct
	public void openLog() throws IOException {
				
		try{
			DailyRollingFileAppender appender=(DailyRollingFileAppender) fileLog.getAppender("BUSINESS_LOG_FILE");
			
			String fileName=appender.getFile();
			appender.setFile(baseDirName+"/"+fileName);
		}catch(Exception e){
			log.warn("log appender not found");
		}
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.manager.demo.imp.LogManager#addLog(java.lang.String)
	 */
	public void doLog(String note) {

		log.info("login log:" + note);

		
		fileLog.info(note);
		
	}

	private String tmp = "Date:${0}:Level:${1}:Context:${2}:Type:${3}";

	@Override
	public void onMessage(Message message) {

		try {
			String logLevel = message.getStringProperty(MsgPropName.LOG);

			SpmsEventType type = SpmsEventType.valueOf(message
					.getStringProperty(MsgPropName.TYPE));

			DateFormat format = SimpleDateFormat
					.getTimeInstance(SimpleDateFormat.FULL);
			String date = format.format(new Date());

			String msg=null;
			if(message instanceof ObjectMessage){
				msg = ((ObjectMessage) message).getObject().toString();
			}else if(message instanceof TextMessage){
				msg=((TextMessage)message).getText();
			}else{
				msg=message.toString();
			}

			String fullMsg = SimpleTmpTool.generByTmp(tmp, date, logLevel, msg,
					type.name());
			log.info("login log:" + fullMsg);
			fileLog.info(fullMsg);
			
		} catch (JMSException e) {
			log.error("jms fail:",e);
		}
	}



//	@Override
//	public void doTrackLog(TrackInfo track) {
//		// TODO Auto-generated method stub
//		
//	}



	@Override
	public void doWfLog(String string) {
		fileLog.info("wf-->"+string);
	}

}
