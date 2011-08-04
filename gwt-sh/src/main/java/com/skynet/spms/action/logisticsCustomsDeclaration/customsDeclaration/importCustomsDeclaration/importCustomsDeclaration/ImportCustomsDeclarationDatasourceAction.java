/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：进口报关单记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ImportCustomsDeclarationDatasourceAction implements DataSourceAction<ImportCustomsDeclaration>{
	@Autowired
	private ImportCustomsDeclarationManager importCustomsDeclarationManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"importCustomsDeclaration_dataSource"};
	}

	/**
	 * 新增进口报关单记录
	 * @param importCustomsDeclaration
	 */
	@Override
	public void insert(ImportCustomsDeclaration importCustomsDeclaration) {
		List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importCustomsDeclaration.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
		importCustomsDeclaration.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
		
		importCustomsDeclarationManager.saveImportCustomsDeclaration(importCustomsDeclaration);
	}

	/**
	 * 更新进口报关单相应记录
	 * @param newValues
	 * @param number
	 * @return
	 */
	@Override
	public ImportCustomsDeclaration update(Map newValues,String number) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("importCustomsStatus")) {
			importCustomsDeclarationManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {
			ImportCustomsDeclaration importCustomsDeclaration = new ImportCustomsDeclaration();
			
			BeanPropUtil.fillEntityWithMap(importCustomsDeclaration, newValues);
			List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importCustomsDeclaration.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
			importCustomsDeclaration.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
			
			return importCustomsDeclarationManager.saveImportCustomsDeclaration(importCustomsDeclaration);		
		}
	}
	
	/**
	 * 删除相应进口报关单记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		importCustomsDeclarationManager.deleteEntity(itemID, ImportCustomsDeclaration.class);
	}
	
	/**
	 * 查询相应进口报关单记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单记录
	 */
	@Override
	public List<ImportCustomsDeclaration> doQuery(Map values,
			int startRow, int endRow) {
		return importCustomsDeclarationManager.getImportCustomsDeclaration(values, 0, -1);
	}

	/**
	 * 获取所有进口报关单记录
	 * @param startRow
	 * @param endRow
	 * @return 进口报关单记录
	 */
	@Override
	public List<ImportCustomsDeclaration> getList(int startRow, int endRow) {
		return importCustomsDeclarationManager.getImportCustomsDeclaration(null, 0, -1);
	}
}
