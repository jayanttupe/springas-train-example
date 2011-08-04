/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDepositManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDeposit;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDeposit;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：出口保证金记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ExportSecurityDepositDatasourceAction implements DataSourceAction<ExportSecurityDeposit>{

	@Autowired
	private ExportSecurityDepositManager exportSecurityDepositManager;
	@Override
	public String[] getBindDsName() {
		return new String[]{"exportSecurityDeposit_dataSource"};
	}					

	/**
	 * 新增出口保证金记录
	 * @param exportSecurityDeposit
	 */
	@Override
	public void insert(ExportSecurityDeposit exportSecurityDeposit) {
		List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportSecurityDeposit.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
		exportSecurityDeposit.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
		
		exportSecurityDepositManager.saveExportSecurityDeposit(exportSecurityDeposit);

	}

	/**
	 * 更新出口保证金记录
	 * @param newValues
	 * @param itemID
	 * @return 出口保证金记录
	 */
	@Override
	public ExportSecurityDeposit update(Map newValues,String itemID) {
		
		ExportSecurityDeposit exportSecurityDeposit = new ExportSecurityDeposit();
		
		BeanPropUtil.fillEntityWithMap(exportSecurityDeposit, newValues);
		List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportSecurityDeposit.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
		exportSecurityDeposit.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
		
		return exportSecurityDepositManager.saveExportSecurityDeposit(exportSecurityDeposit);
	
	}

	/**
	 * 删除相应出口保证金记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		exportSecurityDepositManager.deleteEntity(itemID, ExportSecurityDeposit.class);
	}

	/**
	 * 查询相应出口保证金记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 出口保证金记录
	 */
	@Override
	public List<ExportSecurityDeposit> doQuery(Map values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportSecurityDepositManager.getExportSecurityDeposit(values, 0, -1);
	}

	/**
	 * 获取所有出口保证金记录
	 * @param startRow
	 * @param endRow
	 * @return 出口保证金记录
	 */
	@Override
	public List<ExportSecurityDeposit> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportSecurityDepositManager.getExportSecurityDeposit(null, 0, -1);
	}

}
