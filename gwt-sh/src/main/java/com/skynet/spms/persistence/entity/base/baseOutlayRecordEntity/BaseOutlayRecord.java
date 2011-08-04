package com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */
@MappedSuperclass
public class BaseOutlayRecord extends BaseEntity {

	private String contractNumber;
	private String orderNumber;
	private Float amount;
	public BussinessStatusEntity m_BussinessStatusEntity;
	public BussinessPublishStatusEntity m_BussinessPublishStatusEntity;
	
	/** 供应商送修合同编号 **/
	private String supplierSupportContractId;

	@Column(name = "SUPPLIERSUPPORTCONTRACTID")
	public String getSupplierSupportContractId() {
		return supplierSupportContractId;
	}

	public void setSupplierSupportContractId(String supplierSupportContractId) {
		this.supplierSupportContractId = supplierSupportContractId;
	}

	
	@Column(name="CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name="ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name="AMOUNT")
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_STATUS_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}
	public void setM_BussinessStatusEntity(BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_STATUS_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

}