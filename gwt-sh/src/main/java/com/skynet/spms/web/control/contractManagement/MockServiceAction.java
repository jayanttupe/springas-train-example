package com.skynet.spms.web.control.contractManagement;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.repairmodule.MockService;
import com.skynet.spms.client.vo.repairmodule.MockRecord;
import com.skynet.spms.manager.customerService.RepairService.contract.MockManager;

@Controller
@GwtRpcEndPoint
public class MockServiceAction implements MockService{
	
//	@Resource
//	MockManager manager;

	@Override
	public MockRecord getByContractID(String contractID,String type) {
		MockRecord mockRecord=null;
//		if(MockRecord.MOCK_A.equals(type)){
//			mockRecord=manager.getInspectOutlayRecord(contractID);
//		}else if(MockRecord.MOCK_B.equals(type)){
//			mockRecord=manager.getRepairQutoeRecord(contractID);
//		}
		return mockRecord;
	}

}
