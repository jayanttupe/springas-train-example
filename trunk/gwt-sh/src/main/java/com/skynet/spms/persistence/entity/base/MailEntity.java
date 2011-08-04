package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.MailSendStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:51
 */
public class MailEntity {

	private String operator;
	private int version;
	private Date sendDatetime;
	private String sendMailAddress;
	private String receiveMailAddress;
	private String mailTitle;
	private String  mailBody;
	/**
	 * 发送文件为订舱工作及运代委托书的内容，通过后台生成PDF文件，并依据邮件地址的要求发送给运代商或报关代理商。
	 */
	private String attachmentFile;
	public MailSendStatus m_MailSendStatus;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getSendDatetime() {
		return sendDatetime;
	}
	public void setSendDatetime(Date sendDatetime) {
		this.sendDatetime = sendDatetime;
	}
	public String getSendMailAddress() {
		return sendMailAddress;
	}
	public void setSendMailAddress(String sendMailAddress) {
		this.sendMailAddress = sendMailAddress;
	}
	public String getReceiveMailAddress() {
		return receiveMailAddress;
	}
	public void setReceiveMailAddress(String receiveMailAddress) {
		this.receiveMailAddress = receiveMailAddress;
	}
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	public MailSendStatus getM_MailSendStatus() {
		return m_MailSendStatus;
	}
	public void setM_MailSendStatus(MailSendStatus m_MailSendStatus) {
		this.m_MailSendStatus = m_MailSendStatus;
	}

}