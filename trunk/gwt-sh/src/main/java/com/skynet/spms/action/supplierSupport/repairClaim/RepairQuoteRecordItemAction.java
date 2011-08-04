package com.skynet.spms.action.supplierSupport.repairClaim;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord.IRepairQuoteRecordItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecordItem.RepairQuoteRecordItem;

/**
 * 修理报价明细
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
@Component
public class RepairQuoteRecordItemAction implements
		DataSourceAction<RepairQuoteRecordItem> {

	@Resource
	IRepairQuoteRecordItemManager manager;

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "supplierRepairQuteRecordItem_datasource" };
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(RepairQuoteRecordItem item) {
		manager.addRepairQuoteRecordItem(item);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<RepairQuoteRecordItem> getList(int startRow, int endRow) {
		return manager.queryRepairQuoteRecordItemList(startRow, endRow);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public RepairQuoteRecordItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateRepairQuoteRecordItem(newValues, itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteRepairQuoteRecordItem(itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<RepairQuoteRecordItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String repairQutoReocdId = (String) values.get("repairQutoReocdId");
			return manager.queryRepairQuoteRecordItemListByRecordId(startRow,
					endRow, repairQutoReocdId);
		}
		return null;
	}

}
