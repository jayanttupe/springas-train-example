/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;


import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;

/**
 * 进口报关单明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ImportCustomsDeclarationItemsManager extends CommonManager<ImportCustomsDeclarationItems> {

	/**
	 * 新增进口报关单明细信息
	 * @param importCustomsDeclarationItems
	 */
	public void insertImportCustomsDeclarationItems(ImportCustomsDeclarationItems importCustomsDeclarationItems);
	
	/**
	 * 获取进口报关单明细信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单明细信息
	 */
	public List<ImportCustomsDeclarationItems> getImportCustomsDeclarationItems(Map map, int startRow, int endRow);

}
