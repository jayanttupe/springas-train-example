package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.ContractExecutionStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:41
 */
public class ContractStatusEntity {

	private int version;
	private Date modifyDate;
	private String remarkText;
	private String operator;
	public ContractExecutionStatus m_ContractExecutionStatus;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public ContractExecutionStatus getM_ContractExecutionStatus() {
		return m_ContractExecutionStatus;
	}
	public void setM_ContractExecutionStatus(
			ContractExecutionStatus m_ContractExecutionStatus) {
		this.m_ContractExecutionStatus = m_ContractExecutionStatus;
	}

}