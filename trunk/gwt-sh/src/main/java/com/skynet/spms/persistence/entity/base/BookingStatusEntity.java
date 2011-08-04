package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.BookingMemoStatus;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:37
 */
public class BookingStatusEntity {

	private String operate;
	private int version;
	private String remarkText;
	private Date modifyDate;
	public BookingMemoStatus m_BookingMemoStatus;
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
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
	public BookingMemoStatus getM_BookingMemoStatus() {
		return m_BookingMemoStatus;
	}
	public void setM_BookingMemoStatus(BookingMemoStatus m_BookingMemoStatus) {
		this.m_BookingMemoStatus = m_BookingMemoStatus;
	}

}