package com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairClaimContract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseRepairContractItem.BaseRepairContractItem;
import com.skynet.spms.persistence.entity.csdd.r.RepairProcessCodes;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.s.SerialNumberReturnStatusCode;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 03-四月-2011 11:33:16
 */
@Entity(name="SupplierSupportRepairContractItem")
@Table(name="SPMS_SUPPPAIRCONTRACTITEM")
public class RepairContractItem extends BaseRepairContractItem {

	/** 序号返回状态代码 **/
	private SerialNumberReturnStatusCode m_SerialNumberReturnStatusCode;

	/** 修理厂代码 **/
	private RepairShopCode m_RepairShopCode;

	/** 修理工艺代码 **/
	private RepairProcessCodes m_RepairProcessCodes;

	/** 制造商代码 **/
	private String m_ManufacturerCode;
	
	/**客户送修申请单明细项**/
	private RepairRequisitionSheetItem repairRequisitionSheetItem;
	
	@OneToOne
	@JoinColumn(name="RQSHEETITEM_ID")
	public RepairRequisitionSheetItem getRepairRequisitionSheetItem() {
		return repairRequisitionSheetItem;
	}

	public void setRepairRequisitionSheetItem(
			RepairRequisitionSheetItem repairRequisitionSheetItem) {
		this.repairRequisitionSheetItem = repairRequisitionSheetItem;
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