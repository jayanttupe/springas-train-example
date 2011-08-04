package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookDefer;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:13
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CLEARANCE_ACC_BOOK_DEF")
public class ClearanceAccountBookDefer extends BaseEntity{
    
	/**
	 * 电子账册项号
	 */
	private String accountBookItemsNumber;
	/**
	 * 电子账册号
	 */
	private String clearanceAccountBookNumber;
	/**
	 * 延期批准文号
	 */
	private String deferApproveReferenceNumber;
	/**
	 * 延期备案日期
	 */
	private Date deferRegisterDate;
	/**
	 * 延期序号
	 */
	private String deferSerialNumber;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 备案到期日期
	 */
	private Date TermDate;
	
	private String m_ClearanceAccountBookItems;

	public ClearanceAccountBookDefer(){

	}

	@Column(name = "ACCOUNT_BOOK_ITEMS_NUM")
	public String getAccountBookItemsNumber() {
		return accountBookItemsNumber;
	}

	public void setAccountBookItemsNumber(String accountBookItemsNumber) {
		this.accountBookItemsNumber = accountBookItemsNumber;
	}

	@Column(name = "CLEARANCE_ACCO_BOOK_NUM")
	public String getClearanceAccountBookNumber() {
		return clearanceAccountBookNumber;
	}

	public void setClearanceAccountBookNumber(String clearanceAccountBookNumber) {
		this.clearanceAccountBookNumber = clearanceAccountBookNumber;
	}

	@Column(name = "DEFER_APPROVE_REF_NUM")
	public String getDeferApproveReferenceNumber() {
		return deferApproveReferenceNumber;
	}

	public void setDeferApproveReferenceNumber(String deferApproveReferenceNumber) {
		this.deferApproveReferenceNumber = deferApproveReferenceNumber;
	}

	@Column(name = "DEFER_REGISTER_DATE")
	public Date getDeferRegisterDate() {
		return deferRegisterDate;
	}

	public void setDeferRegisterDate(Date deferRegisterDate) {
		this.deferRegisterDate = deferRegisterDate;
	}

	@Column(name = "DEFER_SERIAL_NUM")
	public String getDeferSerialNumber() {
		return deferSerialNumber;
	}

	public void setDeferSerialNumber(String deferSerialNumber) {
		this.deferSerialNumber = deferSerialNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TERM_DATE")
	public Date getTermDate() {
		return TermDate;
	}

	public void setTermDate(Date termDate) {
		TermDate = termDate;
	}

	@Column(name = "M_CLEARANCE_ACC_BOOK_ITEMS")
	public String getM_ClearanceAccountBookItems() {
		return m_ClearanceAccountBookItems;
	}

	public void setM_ClearanceAccountBookItems(String m_ClearanceAccountBookItems) {
		this.m_ClearanceAccountBookItems = m_ClearanceAccountBookItems;
	}

	public void finalize() throws Throwable {

	}

}