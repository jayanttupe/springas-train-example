package com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.baseSupplyChain.basePartDissembleData.BasePartDisassembleData;

/**
 * 客户部件送修明细项拆换数据
 * 
 * @author tqc
 * @version 1.0
 * @created 28-三月-2011 13:00:21
 */
@Entity
@Table(name = "SPMS_CUSTOMERPARTREPAIRDISDATA")
public class CustomerPartRepairDisassembleData extends BasePartDisassembleData {

	private String repairRequisitionSheetItemID;

	@Column(name="REPAIRREQUISITIONSHEETITEMID")
	public String getRepairRequisitionSheetItemID() {
		return repairRequisitionSheetItemID;
	}

	public void setRepairRequisitionSheetItemID(String repairRequisitionSheetItemID) {
		this.repairRequisitionSheetItemID = repairRequisitionSheetItemID;
	}
	
	
	

}