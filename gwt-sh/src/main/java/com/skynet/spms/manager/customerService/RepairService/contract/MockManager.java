package com.skynet.spms.manager.customerService.RepairService.contract;

import com.skynet.spms.client.vo.repairmodule.MockRecord;

public interface MockManager {

	public MockRecord getInspectOutlayRecord(String contractID);
	
	public MockRecord getRepairQutoeRecord(String contractID);
}
