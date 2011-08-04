package com.skynet.spms.persistence.entity.supplierSupport.consignment.consignProtocol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.customerService.BuybackService.Other.BaseContractItem;

/**
 * 寄售协议明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_CONSIGNPROTOCOLITEM")
public class ConsignmentAgreementItem extends BaseContractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9106766821097025127L;
	/** 寄售地点**/
	private String consignAddr;
	/** 金额 */
	private float price;
	/** 明细所属协议的uuid **/
	private String consignId;

	@Column(name = "CONSIGNID")
	public String getConsignId() {
		return consignId;
	}
	public void setConsignId(String consignId) {
		this.consignId = consignId;
	}
	@Column(name="CONSIGNADDR")
	public String getConsignAddr() {
		return consignAddr;
	}
	public void setConsignAddr(String consignAddr) {
		this.consignAddr = consignAddr;
	}

	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
