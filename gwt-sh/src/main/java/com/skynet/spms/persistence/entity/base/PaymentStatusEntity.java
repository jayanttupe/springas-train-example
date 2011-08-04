package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.PaymentStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:56
 */
public class PaymentStatusEntity {

	private String operator;
	private int version;
	private String remarkText;
	private Date modifyDate;
	public PaymentStatus m_PaymentStatus;
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
	public PaymentStatus getM_PaymentStatus() {
		return m_PaymentStatus;
	}
	public void setM_PaymentStatus(PaymentStatus m_PaymentStatus) {
		this.m_PaymentStatus = m_PaymentStatus;
	}

}