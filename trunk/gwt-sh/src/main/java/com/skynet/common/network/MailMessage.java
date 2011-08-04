package com.skynet.common.network;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.MimeMessageHelper;

public class MailMessage {

	private String from;
	
	private String password;
	
	private String displayName;
	
	private String to;
	
	private String message;
	
	private String subject;
	
	private String[] ccArray;
	
	public void setDisplayName(String name){
		this.displayName=name;
	}
	
	
	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom(){
		return from;
	}

	public void setTo(String to) {
		this.to = to;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public void setCcArray(String[] ccArray) {
		this.ccArray = ccArray;
	}


	private Map<String,File> attachMap=new HashMap<String,File>();

	public void addAttachment(File file){
		attachMap.put(file.getName(), file);
	}

	public void fillMimeMsg(MimeMessageHelper msgHelper) throws MessagingException, UnsupportedEncodingException {
		
		msgHelper.setFrom(from, displayName);
		msgHelper.setTo(to);
		if(ccArray!=null&&ccArray.length>0){
			msgHelper.setCc(ccArray);
		}
		msgHelper.setSubject(subject);		
		msgHelper.setText(message,true);
				
		for(Map.Entry<String, File> entry:attachMap.entrySet()){
			String name=entry.getKey();
			File file=entry.getValue();
			msgHelper.addAttachment(name, file);		
		}
	}

	public void setPwd(String string) {
		password=string;
		
	}


	public String getPassword() {
		return password;
	}
	
	
}
