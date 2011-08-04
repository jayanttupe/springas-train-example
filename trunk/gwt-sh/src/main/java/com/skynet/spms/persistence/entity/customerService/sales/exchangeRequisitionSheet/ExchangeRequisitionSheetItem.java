package com.skynet.spms.persistence.entity.customerService.sales.exchangeRequisitionSheet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:19
 */
@Entity
@Table(name = "SPMS_EXCHANGEREQUISITION_ITEM")
public class ExchangeRequisitionSheetItem extends BaseApplicationFormItem {

	/**
	 * 生产批次号/序号
	 */
	private String batchNumber;
	//public ExchangeContractItem m_ExchangeContractItem;
	//public CustomerPartExchangeDisassembleData m_CustomerPartExchangeDisassembleData;

	@Column(name="BATCHNUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

}