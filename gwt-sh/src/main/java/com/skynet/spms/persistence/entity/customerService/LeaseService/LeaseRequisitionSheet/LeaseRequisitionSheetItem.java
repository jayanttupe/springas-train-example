package com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * @author 曹宏炜
 * @category 客户租赁申请单明细项
 * @version 1.0
 * @created 03-四月-2011 11:32:57
 */
@Entity
@Table(name = "SPMS_LEASEREQUISITIONSHEETITEM")
public class LeaseRequisitionSheetItem extends BaseApplicationFormItem {

	/** 租赁起始日期 **/
	private Date startDate;

	// 租赁结束日期
	private Date endDate;

	// 租赁天数
	private int leaseDays;

	private LeaseContractItem m_LeaseContractItem;

	private InternationalCurrencyCode m_InternationalCurrencyCode;

	// 租赁申请单实体
	private LeaseRequisitionSheet leaseRequisitionSheet;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "LEASE_ID")
	public LeaseRequisitionSheet getLeaseRequisitionSheet() {
		return leaseRequisitionSheet;
	}

	public void setLeaseRequisitionSheet(
			LeaseRequisitionSheet leaseRequisitionSheet) {
		this.leaseRequisitionSheet = leaseRequisitionSheet;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "LEASEDAYS")
	public int getLeaseDays() {
		return leaseDays;
	}

	public void setLeaseDays(int leaseDays) {
		this.leaseDays = leaseDays;
	}

	@OneToOne
	@JoinColumn(name = "LCI_ID")
	public LeaseContractItem getM_LeaseContractItem() {
		return m_LeaseContractItem;
	}

	public void setM_LeaseContractItem(LeaseContractItem m_LeaseContractItem) {
		this.m_LeaseContractItem = m_LeaseContractItem;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INTERCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

}