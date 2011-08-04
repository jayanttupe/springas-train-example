package com.skynet.spms.persistence.entity.customerService.CustomerOrder.answerCustomerConfirmationRepairInspectOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;

/**
 * 答复请求客户确认修理检验指令(由业务人员发布，对供应商业务人员发布的合同给予确认)
 * 
 * @author tqc
 */
@Entity
@Table(name = "SPMS_ANCUSCONREINSPECTORDER")
public class AnswerCustomerConfirmationRepairInspectOrder extends
		BaseOrderEntity {

	/** 客户送修送检指令编号 **/
	private String customerRepairInspectionOrderNumber;

	/** 客户送修送检合同编号 **/
	private String contractNumber;

	/** 是否修理 **/
	private boolean isRepair;

	/** 客户识别代码 **/
	private CustomerIdentificationCode m_CustomerIdentificationCode;

	public AnswerCustomerConfirmationRepairInspectOrder() {
	}

	@Column(name = "CUSREPAIRINSORDER_NUMBER")
	public String getCustomerRepairInspectionOrderNumber() {
		return customerRepairInspectionOrderNumber;
	}

	public void setCustomerRepairInspectionOrderNumber(
			String customerRepairInspectionOrderNumber) {
		this.customerRepairInspectionOrderNumber = customerRepairInspectionOrderNumber;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "ISREPAIR")
	public boolean isRepair() {
		return isRepair;
	}

	public void setRepair(boolean isRepair) {
		this.isRepair = isRepair;
	}

	@ManyToOne
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

}