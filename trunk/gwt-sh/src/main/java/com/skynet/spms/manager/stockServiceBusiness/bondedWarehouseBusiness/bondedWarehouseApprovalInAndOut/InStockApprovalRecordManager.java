package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord.InStockApprovalRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.BusinessScopeAccountBook;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 保税库入库记录Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface InStockApprovalRecordManager extends CommonManager<InStockApprovalRecord>{
	/**
	 * 获取保税库入库记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录
	 */
	public List<InStockApprovalRecord> getInStockApprovalRecord(Map values, int startRow, int endRow);

}