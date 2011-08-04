package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 领料验收单Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CheckAndAcceptSheetManager extends CommonManager<CheckAndAcceptSheet>{

	/**
	 * 获取领料验收单信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 领料验收单信息
	 */
	public List<CheckAndAcceptSheet> getCheckAndAcceptSheet(Map values, int startRow, int endRow);
	
	/**
	 * 保存领料验收单信息
	 * @param checkAndAcceptSheet
	 * @return 领料验收单信息
	 */
	public CheckAndAcceptSheet saveCheckAndAcceptSheet(CheckAndAcceptSheet checkAndAcceptSheet);
}
