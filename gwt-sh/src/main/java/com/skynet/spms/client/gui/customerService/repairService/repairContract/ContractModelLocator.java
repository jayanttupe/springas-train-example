package com.skynet.spms.client.gui.customerService.repairService.repairContract;

import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class ContractModelLocator implements IModelLocator<ContractModelLocator> {
	
	private static ContractModelLocator instance;

	private ContractModelLocator() {
	}

	public static ContractModelLocator getInstance() {
		if (instance == null) {
			instance = new ContractModelLocator();
		}
		return instance;
	}
	
	public RepairContractListGrid repairContractListGrid;
	
	public DataSource customerRepairinsOrderDS;
	
	public DataSource rqContractTemplateDs;
	
	public Record selectedContract;
	
	public String contractID;
	
	public String inspectOutlayRecordId;
	
	public String repairReocrdId;
	
	/** 添加合同所属申请单ID **/
	public String sheetID;
	
	public String viewDetailContractID;


}
