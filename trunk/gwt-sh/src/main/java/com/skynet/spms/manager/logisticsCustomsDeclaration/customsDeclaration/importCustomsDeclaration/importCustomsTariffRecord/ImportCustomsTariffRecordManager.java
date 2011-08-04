/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 进口报关关税业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ImportCustomsTariffRecordManager extends CommonManager<ImportCustomsTariffRecord>{
	
	/**
	 * 获取进口关税相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口关税相关信息
	 */
	public List<ImportCustomsTariffRecord> getImportCustomsTariffRecord(Map values, int startRow, int endRow);

	/**
	 * 保存进口关税相关信息
	 * @param importCustomsTariffRecord
	 * @return 进口关税相关信息
	 */
	public ImportCustomsTariffRecord saveImportCustomsTariffRecord(ImportCustomsTariffRecord importCustomsTariffRecord);
	 
}
