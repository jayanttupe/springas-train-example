/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;

/**
 * 出口报关单明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ExportCustomsDeclarationItemsManager extends CommonManager<ExportCustomsDeclarationItems> {

	/**
	 * 新增出口报关单明细信息
	 * @param exportCustomsDeclarationItems
	 */
	public void insertExportCustomsDeclarationItems(ExportCustomsDeclarationItems exportCustomsDeclarationItems);
	
	/**
	 * 获取出口报关单明细相关信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 出口报关单明细信息
	 */
	public List<ExportCustomsDeclarationItems> getExportCustomsDeclarationItems(Map map, int startRow, int endRow);

}
