package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDepositManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDeposit;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：进口保证金记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ImportSecurityDepositDatasourceAction implements DataSourceAction<ImportSecurityDeposit>{

	@Autowired
	private ImportSecurityDepositManager importSecurityDepositManager;
	@Override
	public String[] getBindDsName() {
		return new String[]{"importSecurityDeposit_dataSource"};
	}

	/**
	 * 新增进口保证金记录
	 * @param importSecurityDeposit
	 */
	@Override
	public void insert(ImportSecurityDeposit importSecurityDeposit) {
		List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importSecurityDeposit.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
		importSecurityDeposit.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
		
		importSecurityDepositManager.saveImportSecurityDeposit(importSecurityDeposit);
	}

	/**
	 * 更新相关进口保证金记录
	 * @param newValues
	 * @param itemID
	 * @return 进口保证金记录
	 */
	@Override
	public ImportSecurityDeposit update(Map newValues,String itemID) {
		
		ImportSecurityDeposit importSecurityDeposit = new ImportSecurityDeposit();
		
		BeanPropUtil.fillEntityWithMap(importSecurityDeposit, newValues);
		List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importSecurityDeposit.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
		importSecurityDeposit.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
		
		return importSecurityDepositManager.saveImportSecurityDeposit(importSecurityDeposit);
	}

	/**
	 * 删除相关进口保证金记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		importSecurityDepositManager.deleteEntity(itemID, ImportSecurityDeposit.class);
	}

	/**
	 * 查询相应进口保证金记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口保证金记录
	 */
	@Override
	public List<ImportSecurityDeposit> doQuery(Map values,
			int startRow, int endRow) {
		return importSecurityDepositManager.getImportSecurityDeposit(values, 0, -1);
	}

	/**
	 * 获取所有进口保证金记录
	 * @param startRow
	 * @param endRow
	 * @return 进口保证金记录
	 */
	@Override
	public List<ImportSecurityDeposit> getList(int startRow, int endRow) {
		return importSecurityDepositManager.getImportSecurityDeposit(null, 0, -1);
	}
}
