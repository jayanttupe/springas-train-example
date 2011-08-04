/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 出口关税业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ExportCustomsTariffRecordManager extends CommonManager<ExportCustomsTariffRecord>{
	
	/**
	 * 获取出口关税相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 出口关税信息
	 */
	 public List<ExportCustomsTariffRecord> getExportCustomsTariffRecord(Map values, int startRow, int endRow);

	 /**
	  * 保存出口关税相关信息
	  * @param exportCustomsTariffRecord
	  * @return 出口关税信息
	  */ 
	 public ExportCustomsTariffRecord saveExportCustomsTariffRecord(ExportCustomsTariffRecord exportCustomsTariffRecord);
	
}
