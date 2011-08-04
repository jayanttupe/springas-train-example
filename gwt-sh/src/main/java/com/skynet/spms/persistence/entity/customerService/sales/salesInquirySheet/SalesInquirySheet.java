package com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet.BaseInquirySheet;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 询价单
 * 
 * @author tqc
 * @version 1.0
 * @created 16-三月-2011 16:02:42
 */
@Entity
@Table(name = "SPMS_SALESINQUIRYSHEET")
public class SalesInquirySheet extends BaseInquirySheet {

	/** 对应询价单明细项 * */
	private List<SalesInquirySheetItem> m_SalesInquirySheetItem;
	/** 客户识别代码 * */
	private CustomerIdentificationCode m_CustomerIdentificationCode;

	/** 工作任务的优先级* */
	private Priority m_Priority;
	
	@OneToMany(mappedBy = "salesInquirySheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<SalesInquirySheetItem> getM_SalesInquirySheetItem() {
		return m_SalesInquirySheetItem;
	}

	public void setM_SalesInquirySheetItem(
			List<SalesInquirySheetItem> m_SalesInquirySheetItem) {
		this.m_SalesInquirySheetItem = m_SalesInquirySheetItem;
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