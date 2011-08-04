package com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseLeaseContractItem.BaseLeaseContractItem;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate.SSLeaseContractTemplate;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:23
 */
@Entity
@Table(name = "SPMS_SSLEASECONTRACTITEM")
public class SSLeaseContractItem extends BaseLeaseContractItem {

	// public List<OrderPartItem> m_OrderPartItem;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SSLeaseContractTemplate ssleaseContractTemplate;
	// 手续费
	private Float factorage = 0.0f;
	// 总价格
	private Float price = 0.0f;

	@Column(name = "FACTORAGE")
	public Float getFactorage() {
		return factorage;
	}

	public void setFactorage(Float factorage) {
		this.factorage = factorage;
	}

	@Column(name = "PRICE")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "LEASECONTRACTTEMPLATE_ID")
	public SSLeaseContractTemplate getSsleaseContractTemplate() {
		return ssleaseContractTemplate;
	}

	public void setSsleaseContractTemplate(
			SSLeaseContractTemplate ssleaseContractTemplate) {
		this.ssleaseContractTemplate = ssleaseContractTemplate;
	}

}