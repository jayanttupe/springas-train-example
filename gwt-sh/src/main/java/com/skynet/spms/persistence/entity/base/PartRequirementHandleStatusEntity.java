package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.PartRequirementHandleStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:55
 */
public class PartRequirementHandleStatusEntity {

	private String opreator;
	private int version;
	private String remarkText;
	private Date modifyDate;
	public PartRequirementHandleStatus m_PartRequirementHandleStatus;
	public String getOpreator() {
		return opreator;
	}
	public void setOpreator(String opreator) {
		this.opreator = opreator;
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
	public PartRequirementHandleStatus getM_PartRequirementHandleStatus() {
		return m_PartRequirementHandleStatus;
	}
	public void setM_PartRequirementHandleStatus(
			PartRequirementHandleStatus m_PartRequirementHandleStatus) {
		this.m_PartRequirementHandleStatus = m_PartRequirementHandleStatus;
	}

}