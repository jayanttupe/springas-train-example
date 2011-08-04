package com.skynet.spms.persistence.entity.baseSupplyChain;

import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.vanning.Vanning;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * 装箱报告
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:57
 */
public class VanningControl extends BaseIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9138366812599294976L;
	private String caseNumber;
	private String contractNumber;
	private String orderNumber;
	private int itemNumber;
	public Vanning m_Vanning;
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public Vanning getM_Vanning() {
		return m_Vanning;
	}
	public void setM_Vanning(Vanning m_Vanning) {
		this.m_Vanning = m_Vanning;
	}

}