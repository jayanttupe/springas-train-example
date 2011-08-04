
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 进口报关单信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ImportCustomsDeclarationManager extends CommonManager<ImportCustomsDeclaration>{
	
	/**
	 * 保存进口报关单相关信息
	 * @param importCustomsDeclaration
	 * @return
	 */
	public ImportCustomsDeclaration saveImportCustomsDeclaration(ImportCustomsDeclaration importCustomsDeclaration);
	
	/**
	 * 获取进口报关单信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单信息
	 */
	 public List<ImportCustomsDeclaration> getImportCustomsDeclaration(Map values, int startRow, int endRow);

	 /**
	  * 设置工作状态
	  * @param orderID
	  */
	 public void setWorkStatus(String orderID);
}
