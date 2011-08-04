/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJob;
import com.skynet.spms.tools.OneToManyTools;
/**
 * 描述：出口报关单记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ExportCustomsDeclarationDatasourceAction implements DataSourceAction<ExportCustomsDeclaration>{
    
	@Autowired
	private ExportCustomsDeclarationManager exportCustomsDeclarationManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"exportCustomsDeclaration_dataSource"};
	}

	/**
	 * 新增出口报关单记录
	 * @param exportCustomsDeclaration
	 */
	@Override
	public void insert(ExportCustomsDeclaration exportCustomsDeclaration) {
		List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportCustomsDeclaration.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
		exportCustomsDeclaration.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
		exportCustomsDeclarationManager.saveExportCustomsDeclaration(exportCustomsDeclaration);
				
	}

	/**
	 * 更新出口报关单记录
	 * @param newValues
	 * @param itemID
	 * @return 出口报关单记录
	 */
	@Override
	public ExportCustomsDeclaration update(Map newValues,String itemID) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("exportCustomsStatus")) {
			exportCustomsDeclarationManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {	
			ExportCustomsDeclaration exportCustomsDeclaration = new ExportCustomsDeclaration();
			
			BeanPropUtil.fillEntityWithMap(exportCustomsDeclaration, newValues);
			List<ExportCustomsDeclarationItems> newExportCustomsDeclarationItemsList = OneToManyTools.ConvertToEntity(exportCustomsDeclaration.getExportCustomsDeclarationItems(), ExportCustomsDeclarationItems.class);
			exportCustomsDeclaration.setExportCustomsDeclarationItems(newExportCustomsDeclarationItemsList);
			
			return exportCustomsDeclarationManager.saveExportCustomsDeclaration(exportCustomsDeclaration);		
		}		
	}

	/**
	 * 删除相应出口报关单记录
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		exportCustomsDeclarationManager.deleteEntity(itemID, ExportCustomsDeclaration.class);
		
	}

	/**
	 * 查询相应出口报关单记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	@Override
	public List<ExportCustomsDeclaration> doQuery(Map values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportCustomsDeclarationManager.getExportCustomsDeclaration(values, 0, -1);
	}

	/**
	 * 获取所有出口报关单记录
	 * @param startRow
	 * @param endRow
	 * @return 出口报关单记录
	 */
	@Override
	public List<ExportCustomsDeclaration> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return exportCustomsDeclarationManager.getExportCustomsDeclaration(null, 0, -1);
	}

}
