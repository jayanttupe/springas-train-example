package com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheet;

/**
 * 客户送修申请单明细项
 * 
 * @author tqc
 * @version 1.0
 * @created 28-三月-2011 13:00:22
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_REPAIRREQSHEETITEM")
public class RepairRequisitionSheetItem extends BaseApplicationFormItem {
	/** 关联送修单* */
	private RepairRequisitionSheet repairRequisitionSheet;

	/** 生产批次号/序号 **/
	private String batchNumber;
	
	/**原始合同编号**/
	private String oldContractNumber;
	
	@Column(name = "BATCH_NUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@OneToOne(mappedBy = "repairRequisitionSheetItem", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public RepairRequisitionSheet getRepairRequisitionSheet() {
		return repairRequisitionSheet;
	}

	public void setRepairRequisitionSheet(
			RepairRequisitionSheet repairRequisitionSheet) {
		this.repairRequisitionSheet = repairRequisitionSheet;
	}
	
	@Column(name="OLDCONTRACTNUMBER")
	public String getOldContractNumber() {
		return oldContractNumber;
	}

	public void setOldContractNumber(String oldContractNumber) {
		this.oldContractNumber = oldContractNumber;
	}
	
	

}