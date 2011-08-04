package com.skynet.spms.persistence.entity.customerService.CustomerOrder.customerRepairInspectionOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;

/**
 * 客户送修送检指令
 * 
 * @author tqc
 * 
 */
@Entity
@Table(name = "SPMS_CUSTOMERREPAIRINSORDER")
public class CustomerRepairInspectionOrder extends BaseOrderEntity {

	/** 客户送修送检合同编号 **/
	private String contractNumber;

	/** 客户识别代码 **/
	private CustomerIdentificationCode m_CustomerIdentificationCode;

	/** 运代商 **/
	private CarrierName m_CarrierName;

	/** 联系人 **/
	private String linkMan;

	/** 联系方式 **/
	private String linkWay;
	
	/**客户合同编号**/
	private String contractTemplateID;

	@Column(name="CONTRACTTEMPLATEID")
	public String getContractTemplateID() {
		return contractTemplateID;
	}

	public void setContractTemplateID(String contractTemplateID) {
		this.contractTemplateID = contractTemplateID;
	}

	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "LINKWAY")
	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	@Column(name = "CONTRACT_NUM")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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

	@ManyToOne
	@JoinColumn(name = "CARRIERNAME_ID")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}

	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}

}