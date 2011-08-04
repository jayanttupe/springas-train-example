package com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseRepairContractItem;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseItem.BasePartItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * 基础修理合同明细项
 * 
 * @author tqc
 * @version 1.0
 * @created 10-三月-2011 11:10:35
 */

@MappedSuperclass
public class BaseRepairContractItem extends BasePartItem {

	/** 生产批次号/序号 **/
	private String batchNumber;

	/** 修理费 **/
	private Float repairFee;

	/** 海关参考价 **/
	private Float customsReferencePrice;

	/** 材料返回授权 **/
	private Integer returnAuthorizationNumber;

	/** 返回授权号 **/
	private Integer materialReturnAuthorization;

	/** 单位代码 **/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	
	/** 币种 */
	private InternationalCurrencyCode currency;
	

	@Column(name = "BATCH_NUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "REPAIR_FEE")
	public Float getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(Float repairFee) {
		this.repairFee = repairFee;
	}

	@Column(name = "CP_PRICE")
	public Float getCustomsReferencePrice() {
		return customsReferencePrice;
	}

	public void setCustomsReferencePrice(Float customsReferencePrice) {
		this.customsReferencePrice = customsReferencePrice;
	}

	@Column(name = "RA_NUMBER")
	public Integer getReturnAuthorizationNumber() {
		return returnAuthorizationNumber;
	}

	public void setReturnAuthorizationNumber(Integer returnAuthorizationNumber) {
		this.returnAuthorizationNumber = returnAuthorizationNumber;
	}

	@Column(name = "MR_AUTHORIZATION")
	public Integer getMaterialReturnAuthorization() {
		return materialReturnAuthorization;
	}

	public void setMaterialReturnAuthorization(
			Integer materialReturnAuthorization) {
		this.materialReturnAuthorization = materialReturnAuthorization;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UOM_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY_CODE")
	public InternationalCurrencyCode getCurrency() {
		return currency;
	}

	public void setCurrency(InternationalCurrencyCode currency) {
		this.currency = currency;
	}
	
	

}