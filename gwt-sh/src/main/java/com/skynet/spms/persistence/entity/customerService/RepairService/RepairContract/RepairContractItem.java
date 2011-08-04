package com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseRepairContractItem.BaseRepairContractItem;
import com.skynet.spms.persistence.entity.csdd.r.RepairProcessCodes;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.s.SerialNumberReturnStatusCode;
import com.skynet.spms.persistence.entity.csdd.w.WarrantyTimeCycleReferenceCode;

/**
 * 送修合同明细项域
 * 
 * @author tqc
 * @version 1.0
 * @created 28-三月-2011 13:00:21
 */
@Entity
@Table(name = "SPMS_REPAIRCONTRACTITEM")
public class RepairContractItem extends BaseRepairContractItem {

	/** 序号返回状态代码 **/
	private SerialNumberReturnStatusCode m_SerialNumberReturnStatusCode;

	/** 修理厂代码 **/
	private RepairShopCode m_RepairShopCode;

	/** 修理工艺代码 **/
	private RepairProcessCodes m_RepairProcessCodes;

	/** 制造商代码 **/
	private String m_ManufacturerCode;
	
	/**原始合同号**/
	private String oldContractNumber;
	
	/**担保时间/循环代码**/
	private WarrantyTimeCycleReferenceCode cycleReferenceCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CYCLEREFERENCE_CODE")
	public WarrantyTimeCycleReferenceCode getCycleReferenceCode() {
		return cycleReferenceCode;
	}

	public void setCycleReferenceCode(
			WarrantyTimeCycleReferenceCode cycleReferenceCode) {
		this.cycleReferenceCode = cycleReferenceCode;
	}

	@Column(name="OLDCONTRACTNUMBER")
	public String getOldContractNumber() {
		return oldContractNumber;
	}

	public void setOldContractNumber(String oldContractNumber) {
		this.oldContractNumber = oldContractNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SNRS_CODE")
	public SerialNumberReturnStatusCode getM_SerialNumberReturnStatusCode() {
		return m_SerialNumberReturnStatusCode;
	}

	public void setM_SerialNumberReturnStatusCode(
			SerialNumberReturnStatusCode m_SerialNumberReturnStatusCode) {
		this.m_SerialNumberReturnStatusCode = m_SerialNumberReturnStatusCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "RSHOP_CODE")
	public RepairShopCode getM_RepairShopCode() {
		return m_RepairShopCode;
	}

	public void setM_RepairShopCode(RepairShopCode m_RepairShopCode) {
		this.m_RepairShopCode = m_RepairShopCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "RPROCESS_CODE")
	public RepairProcessCodes getM_RepairProcessCodes() {
		return m_RepairProcessCodes;
	}

	public void setM_RepairProcessCodes(RepairProcessCodes m_RepairProcessCodes) {
		this.m_RepairProcessCodes = m_RepairProcessCodes;
	}

	@Column(name = "MANUFACTURER_CODE")
	public String getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(String m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

}