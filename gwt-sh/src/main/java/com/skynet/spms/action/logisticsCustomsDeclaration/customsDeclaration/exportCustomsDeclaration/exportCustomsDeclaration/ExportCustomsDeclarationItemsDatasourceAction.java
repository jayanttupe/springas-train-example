package com.skynet.spms.action.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;

/**
 * 描述：出口报关单明细记录
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ExportCustomsDeclarationItemsDatasourceAction implements DataSourceAction<ExportCustomsDeclarationItems>{

	   private Logger log=LoggerFactory.getLogger(ExportCustomsDeclarationItemsDatasourceAction.class);
	   
		@Autowired
		private ExportCustomsDeclarationItemsManager exportCustomsDeclarationItemsManager; 
		
		@Override
		public String[] getBindDsName() {
			// TODO Auto-generated method stub
			return new String[]{"exportCustomsDeclarationItems_dataSource"};
		}

		/**
		 * 新增出口报关单明细记录
		 * @param exportCustomsDeclarationItems
		 */
		@Override
		public void insert(ExportCustomsDeclarationItems exportCustomsDeclarationItems) {
			// TODO Auto-generated method stub
			exportCustomsDeclarationItemsManager.insertEntity(exportCustomsDeclarationItems);
		}

		/**
		 * 更新出口报关单明细记录
		 * @param newValues
		 * @param itemID
		 * @return 出口报关单明细记录
		 */
		@Override
		public ExportCustomsDeclarationItems update(Map newValues,
				String itemID) {
			// TODO Auto-generated method stub
			return exportCustomsDeclarationItemsManager.updateEntity(newValues, itemID, ExportCustomsDeclarationItems.class);
		}
		
		/**
		 * 删除出口报关单明细记录
		 * @param itemID
		 */
		@Override
		public void delete(String itemID) {
			// TODO Auto-generated method stub
			exportCustomsDeclarationItemsManager.deleteEntity(itemID, ExportCustomsDeclarationItems.class);
		}

		/**
		 * 查询相应出口报关单明细记录
		 * @param values
		 * @param startRow
		 * @param endRow
		 * @return 出口报关单明细记录
		 */
		@Override
		public List<ExportCustomsDeclarationItems> doQuery(
				Map values, int startRow, int endRow) {
			// TODO Auto-generated method stub
			return exportCustomsDeclarationItemsManager.getExportCustomsDeclarationItems(values, startRow, endRow);
		}

		/**
		 * 获取所有出口报关单明细记录
		 * @param startRow
		 * @param endRow
		 * @return 出口报关单明细记录
		 */
		@Override
		public List<ExportCustomsDeclarationItems> getList(int startRow, int endRow) {
			// TODO Auto-generated method stub
			return exportCustomsDeclarationItemsManager.getExportCustomsDeclarationItems(null, 0, -1);
		}


}
