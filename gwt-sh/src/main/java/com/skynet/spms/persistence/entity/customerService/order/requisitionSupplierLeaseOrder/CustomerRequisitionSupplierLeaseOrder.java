package com.skynet.spms.persistence.entity.customerService.order.requisitionSupplierLeaseOrder;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * 客户申请供应商租赁指令，是当客户业务人员发起客户租赁业务时发现本身库房没有户所需要的租赁航材，且目前采购订单或者采购计划中均没有，
 * 则触发向供应商业务人员请求供应 商租赁业务的需求。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:15
 */
@Entity
@Table(name = "SPMS_CRSLEASEORDER")
public class CustomerRequisitionSupplierLeaseOrder extends BaseOrderEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 客户租赁合同编号 **/
	private String contractNumber;
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	private CarrierName m_CarrierName;
	private LeaseContractTemplate m_LeaseContractTemplate;
	private List<LeaseContractItem> m_LeaseContractItem;

	/** 联系人 **/
	private String linkman;

	/** 联系方式 **/
	private String contactInformation;

	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "CONTACTINFORMATION")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Column(name = "CONTRACTNUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@ManyToOne
	@JoinColumn(name = "M_CUSTOMEONCODE")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

	@ManyToOne
	@JoinColumn(name = "M_CARRIERNAME")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}

	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}

	@OneToOne
	@JoinColumn(name = "M_LEASECONTRACTTEMPLATE")
	public LeaseContractTemplate getM_LeaseContractTemplate() {
		return m_LeaseContractTemplate;
	}

	public void setM_LeaseContractTemplate(
			LeaseContractTemplate m_LeaseContractTemplate) {
		this.m_LeaseContractTemplate = m_LeaseContractTemplate;
	}

	@OneToMany
	@JoinColumn(name = "M_LEASCTITEM")
	public List<LeaseContractItem> getM_LeaseContractItem() {
		return m_LeaseContractItem;
	}

	public void setM_LeaseContractItem(
			List<LeaseContractItem> m_LeaseContractItem) {
		this.m_LeaseContractItem = m_LeaseContractItem;
	}

}