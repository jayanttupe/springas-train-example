package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model;

import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplateListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class ModelLocator implements IModelLocator<ModelLocator> {
	
	private static ModelLocator instance;

	private ModelLocator() {
	}

	public static ModelLocator getInstance() {
		if (instance == null) {
			instance = new ModelLocator();
		}
		return instance;
	}
	
	/**送修单**/
	public RepairInspectClaimContractTemplateListGrid repairInsClaimContractListGrid;

	/**选中送修记录指令**/
	public Record selectedRecord;

	/** 送修合同数据源 **/
	public DataSource rqContractTemplateDs;

	/** 添加合同主表记录 **/
	public String contractID;
	
	/**供应商检验费用处理记录id**/
	public String inspectOutlayRecordId;
	
	public String repairReocrdId;
	
	public String viewDetailContractID;
	

}
