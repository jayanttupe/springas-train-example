package com.skynet.spms.action.supplierSupport.procurement.procurementTransferSheet.procurementTransferSheetItem;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem.IProcurementTransferSheetItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem;
/**
 *采购调拨单明细
 * @author 李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementTransferSheetItemAction implements
		DataSourceAction<ProcurementTransferSheetItem> {

	@Resource
	IProcurementTransferSheetItemManager manager;
	
	@Autowired
	private ListGridFilterManager<ProcurementTransferSheetItem> filterManager;

	public String[] getBindDsName() {
		return new String[] { "transferSheetItem_datasource" };
	}

	/**
	 * 添加询价单明细
	 * 
	 * @param o
	 *            待添加的询价单明细
	 */
	public void insert(ProcurementTransferSheetItem item) {
		manager.addProcurementTransferSheetItem(item);
	}

	
	/**
	 * 删除询价单明细
	 * 
	 * @param itemID
	 *            询价单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementTransferSheetItem(itemID);
	}

	
	/**
	 * 更新询价单明细
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            询价单明细Id
	 * @return 询价单明细对象
	 */
	public ProcurementTransferSheetItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementTransferSheetItem(newValues, itemID);
	}

	
	/**
	 * 列表查询
	 * 
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数
	 * @return 询价单明细列表
	 */
	public List<ProcurementTransferSheetItem> getList(int startRow, int endRow) {
		return manager.queryProcurementTransferSheetItemList(startRow, endRow);
	}
	
	/**
	 * 列表筛选
	 * 
	 * @param values
	 *            筛选条件
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数
	 * @return 询价单明细列表
	 */
	public List<ProcurementTransferSheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(ProcurementTransferSheetItem.class,
					criteria, startRow, endRow);
		}
		
		List<ProcurementTransferSheetItem> items;
		//根据调拨单id获得调拨单明细信息
		if (values.get("id")!=null) {
			items = manager
					.queryProcurementTransferSheetItemByTransferId(values.get(
							"id").toString());
			return items;
		}
		return null;
	}

}
