package com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet;
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

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesOrder.SalesOrder.SalesOrder;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 客户销售申请单
 * 
 * @author tqc
 * @version 1.0
 * @created 16-三月-2011 16:02:43
 */
@Entity
@Table(name = "SPMS_SALESREQUISITIONSHEET")
public class SalesRequisitionSheet extends BaseApplicationForm {

	/**
	 * 客户销售订单号，由客户自己系统产生并维护，属于客户系统范畴内的工作流水号。客户可依据客户销售订单号对销售申请以及销售订单进行查询。
	 */
	private String customerPurchasingOrderNumber;
	/**
	 * 对应销售订单
	 */
	public SalesOrder m_SalesOrder;
	/***
	 * 销售订单明细
	 */
	public List<SalesRequisitionSheetItem> m_SalesRequisitionSheetItem;
	/***
	 * 优先级
	 */
	public Priority m_Priority;
	/***
	 * 客户识别代码
	 */
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	/***
	 * 合同是否存在
	 */
	private Boolean isTemptlate;
	
	
	private String quotationId;

	@Column(name = "QUOTATION_ID")
	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	@Column(name = "ISTEMPTLATE")
	public Boolean getIsTemptlate() {
		return isTemptlate;
	}

	public void setIsTemptlate(Boolean isTemptlate) {
		this.isTemptlate = isTemptlate;
	} 
	
	private Integer itemCount;
	
	@Column(name = "ITEMCOUNT")
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	@Column(name = "CUSTOMER_ORDER_NUM")
	public String getCustomerPurchasingOrderNumber() {
		return customerPurchasingOrderNumber;
	}
	public void setCustomerPurchasingOrderNumber(
			String customerPurchasingOrderNumber) {
		this.customerPurchasingOrderNumber = customerPurchasingOrderNumber;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SO_ID")
	public SalesOrder getM_SalesOrder() {
		return m_SalesOrder;
	}
	public void setM_SalesOrder(SalesOrder salesOrder) {
		m_SalesOrder = salesOrder;
	}
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SRSITEM_ID")
	public List<SalesRequisitionSheetItem> getM_SalesRequisitionSheetItem() {
		return m_SalesRequisitionSheetItem;
	}
	public void setM_SalesRequisitionSheetItem(
			List<SalesRequisitionSheetItem> salesRequisitionSheetItem) {
		m_SalesRequisitionSheetItem = salesRequisitionSheetItem;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority priority) {
		m_Priority = priority;
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

}