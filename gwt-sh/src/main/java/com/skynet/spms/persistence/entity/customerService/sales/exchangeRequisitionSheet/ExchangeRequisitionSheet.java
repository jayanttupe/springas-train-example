package com.skynet.spms.persistence.entity.customerService.sales.exchangeRequisitionSheet;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:19
 */
@Entity
@Table(name = "SPMS_EXCHANGEREQUISITION")
public class ExchangeRequisitionSheet extends BaseApplicationForm {

	/**
	 * 备件交换日期是指备件交货给客户的日期。
	 */
	private Date deliveryDate;
	
	/***
	 * 交货明细
	 */
	public ExchangeRequisitionSheetItem exchangeRequisitionSheetItem;
	//public ExchangeContractTemplate m_ExchangeContractTemplate;
	
	/***
	 * 客户
	 */
	public CustomerIdentificationCode m_CustomerIdentificationCode;

	
	/***
	 * 优先级
	 */
	public Priority m_Priority;
	
	@Column(name="DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "EXCHANGEREQUISITIONSHEET_ITEM")
	public ExchangeRequisitionSheetItem getExchangeRequisitionSheetItem() {
		return exchangeRequisitionSheetItem;
	}


	public void setExchangeRequisitionSheetItem(
			ExchangeRequisitionSheetItem exchangeRequisitionSheetItem) {
		this.exchangeRequisitionSheetItem = exchangeRequisitionSheetItem;
	}
	
	@ManyToOne
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode customerIdentificationCode) {
		m_CustomerIdentificationCode = customerIdentificationCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}

}