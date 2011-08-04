package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：进口关税记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ImportCustomsTariffRecordDatasourceAction implements DataSourceAction<ImportCustomsTariffRecord>{
   
	@Autowired
	private ImportCustomsTariffRecordManager importCustomsTariffRecordManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"importCustomsTariffRecord_dataSource"};
	}

	/**
	 * 新增进口关税记录
	 * @param importCustomsTariffRecord
	 */
	@Override
	public void insert(ImportCustomsTariffRecord importCustomsTariffRecord) {
		List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importCustomsTariffRecord.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
		importCustomsTariffRecord.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
		
		importCustomsTariffRecordManager.saveImportCustomsTariffRecord(importCustomsTariffRecord);
	}

	/**
	 * 更新相关进口关税记录
	 * @param newValues
	 * @param number
	 * @return 进口关税记录
	 */
	@Override
	public ImportCustomsTariffRecord update(Map newValues,String number) {
		
		ImportCustomsTariffRecord importCustomsTariffRecord = new ImportCustomsTariffRecord();
		
		BeanPropUtil.fillEntityWithMap(importCustomsTariffRecord, newValues);
		List<ImportCustomsDeclarationItems> newImportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(importCustomsTariffRecord.getImportCustomsDeclarationItems(), ImportCustomsDeclarationItems.class);
		importCustomsTariffRecord.setImportCustomsDeclarationItems(newImportCustomsDeclarationItemsList);
		
		return importCustomsTariffRecordManager.saveImportCustomsTariffRecord(importCustomsTariffRecord);
	
	}

	/**
	 * 删除相关进口关税记录
	 * @param number
	 */
	@Override
	public void delete(String number) {
		// TODO Auto-generated method stub
		importCustomsTariffRecordManager.deleteEntity(number, ImportCustomsTariffRecord.class);
	}

	/**
	 * 查询相关进口关税记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口关税记录
	 */
	@Override
	public List<ImportCustomsTariffRecord> doQuery(Map values,int startRow, int endRow) {
		// TODO Auto-generated method stub
		return importCustomsTariffRecordManager.getImportCustomsTariffRecord(values, 0, -1);
	}

	/**
	 * 获取所有进口关税记录
	 * @param startRow
	 * @param endRow
	 * @return 进口关税记录
	 */
	@Override
	public List<ImportCustomsTariffRecord> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return importCustomsTariffRecordManager.getImportCustomsTariffRecord(null, 0, -1);
	}

}

