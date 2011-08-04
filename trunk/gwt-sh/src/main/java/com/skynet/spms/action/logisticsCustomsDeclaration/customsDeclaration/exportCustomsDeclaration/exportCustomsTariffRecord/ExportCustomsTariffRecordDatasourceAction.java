package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：出口关税记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ExportCustomsTariffRecordDatasourceAction implements DataSourceAction<ExportCustomsTariffRecord>{

	@Autowired
	private ExportCustomsTariffRecordManager exportCustomsTariffRecordManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"exportCustomsTariffRecord_dataSource"};
	}

	/**
	 * 新增出口关税记录
	 * @param exportCustomsTariffRecord
	 */
	@Override
	public void insert(ExportCustomsTariffRecord exportCustomsTariffRecord) {
		List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportCustomsTariffRecord.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
		exportCustomsTariffRecord.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
		
		exportCustomsTariffRecordManager.saveExportCustomsTariffRecord(exportCustomsTariffRecord);
		}
	
	/**
	 * 更新相应出口关税记录
	 * @param newValues
	 * @param itemID
	 * @return 出口关税记录
	 */
	@Override
	public ExportCustomsTariffRecord update(Map newValues,String itemID) {
		
		ExportCustomsTariffRecord exportCustomsTariffRecord = new ExportCustomsTariffRecord();
		
		BeanPropUtil.fillEntityWithMap(exportCustomsTariffRecord, newValues);
		List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportCustomsTariffRecord.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
		exportCustomsTariffRecord.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
		
		return exportCustomsTariffRecordManager.saveExportCustomsTariffRecord(exportCustomsTariffRecord);

	}
	
	/**
	 * 删除出口关税记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		exportCustomsTariffRecordManager.deleteEntity(itemID, ExportCustomsTariffRecord.class);
	}

	/**
	 * 查询相应出口关税记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 出口关税记录
	 */
	@Override
	public List<ExportCustomsTariffRecord> doQuery(Map values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportCustomsTariffRecordManager.getExportCustomsTariffRecord(values, 0, -1);
	}

	/**
	 * 获取所有出口关税记录
	 * @param startRow
	 * @param endRow
	 * @return 出口关税记录
	 */
	@Override
	public List<ExportCustomsTariffRecord> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportCustomsTariffRecordManager.getExportCustomsTariffRecord(null, 0, -1);
	}

}

