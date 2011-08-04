package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.GeneralJobStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:46
 */
public class GeneralJobStatusEntity {

	private String operator;
	private int version;
	private String remarkText;
	private Date modifyDate;
	public GeneralJobStatus m_GeneralJobStatus;
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
	public GeneralJobStatus getM_GeneralJobStatus() {
		return m_GeneralJobStatus;
	}
	public void setM_GeneralJobStatus(GeneralJobStatus m_GeneralJobStatus) {
		this.m_GeneralJobStatus = m_GeneralJobStatus;
	}

}