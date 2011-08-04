package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.DocumentStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:43
 */
public class DocumentStatusEntity {

	private String operator;
	private int version;
	private String remarkText;
	private Date modifyDate;
	public DocumentStatus m_DocumentStatus;
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
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public DocumentStatus getM_DocumentStatus() {
		return m_DocumentStatus;
	}
	public void setM_DocumentStatus(DocumentStatus m_DocumentStatus) {
		this.m_DocumentStatus = m_DocumentStatus;
	}

}