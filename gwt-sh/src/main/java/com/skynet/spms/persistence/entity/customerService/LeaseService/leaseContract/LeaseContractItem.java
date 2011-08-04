package com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseLeaseContractItem.BaseLeaseContractItem;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;

/**
 * @author 曹宏炜
 * @category 客户租赁合同明细项
 * @version 1.0
 * @created 03-四月-2011 11:32:56
 */
@Entity
@Table(name = "SPMS_LEASECONTRACTITEM")
public class LeaseContractItem extends BaseLeaseContractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private LeaseContractPostponeRequsitionSheetItem
	// m_LeaseContractPostponeRequsitionSheetItem;
	// 手续费
	private float factorage;

	// 总价格
	private Float price = 0.0f;

	// private VanningControl m_VanningControl;

	@Column(name = "PRICE")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "FACTORAGE")
	public float getFactorage() {
		return factorage;
	}

	public void setFactorage(float factorage) {
		this.factorage = factorage;
	}

	private LeaseContractTemplate leaseContractTemplate;

	@ManyToOne
	@JoinColumn(name = "LEASECONTRACTTEMPLATE_ID")
	public LeaseContractTemplate getLeaseContractTemplate() {
		return leaseContractTemplate;
	}

	public void setLeaseContractTemplate(
			LeaseContractTemplate leaseContractTemplate) {
		this.leaseContractTemplate = leaseContractTemplate;
	}

	// @OneToOne
	// @JoinColumn(name = "LCPRSI_ID")
	// public LeaseContractPostponeRequsitionSheetItem
	// getM_LeaseContractPostponeRequsitionSheetItem() {
	// return m_LeaseContractPostponeRequsitionSheetItem;
	// }
	//
	// public void setM_LeaseContractPostponeRequsitionSheetItem(
	// LeaseContractPostponeRequsitionSheetItem
	// m_LeaseContractPostponeRequsitionSheetItem) {
	// this.m_LeaseContractPostponeRequsitionSheetItem =
	// m_LeaseContractPostponeRequsitionSheetItem;
	// }

}