/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;

/**
 * 描述：进口报关单明细记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ImportCustomsDeclarationItemsDatasourceAction implements DataSourceAction<ImportCustomsDeclarationItems>{

   private Logger log=LoggerFactory.getLogger(ImportCustomsDeclarationItemsDatasourceAction.class);
	
	@Autowired
	private ImportCustomsDeclarationItemsManager importCustomsDeclarationItemsManager; 
	@Override
	public String[] getBindDsName() {
		return new String[]{"importCustomsDeclarationItems_dataSource"};
	}

	/**
	 * 新增进口报关单明细记录
	 * @param importCustomsDeclarationItems
	 */
	@Override
	public void insert(ImportCustomsDeclarationItems importCustomsDeclarationItems) {
		importCustomsDeclarationItemsManager.insertEntity(importCustomsDeclarationItems);
	}

	/**
	 * 更新进口报关单明细记录
	 * @param newValues
	 * @param itemID
	 * @return 进口报关单明细记录
	 */
	@Override
	public ImportCustomsDeclarationItems update(Map newValues,
			String itemID) {
		return importCustomsDeclarationItemsManager.updateEntity(newValues, itemID, ImportCustomsDeclarationItems.class);
	}
	
	/**
	 * 删除进口报关单明细记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		importCustomsDeclarationItemsManager.deleteEntity(itemID, ImportCustomsDeclarationItems.class);
	}

	/**
	 * 查询相关进口报关单明细记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单明细记录
	 */
	@Override
	public List<ImportCustomsDeclarationItems> doQuery(
			Map values, int startRow, int endRow) {
		return importCustomsDeclarationItemsManager.getImportCustomsDeclarationItems(values, startRow, endRow);
	}

	/**
	 * 获取所有进口报关单明细记录
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单明细记录
	 */
	@Override
	public List<ImportCustomsDeclarationItems> getList(int startRow, int endRow) {
		return importCustomsDeclarationItemsManager.getImportCustomsDeclarationItems(null, startRow, endRow);
	}
}
