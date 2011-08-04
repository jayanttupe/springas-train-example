package com.skynet.spms.action.supplierSupport.repairClaim;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord.IInspectOutlayRecordItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecordItem;

/**
 * 修理记录明细
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
@Component
public class InspectOutlayRecordItemAction implements
		DataSourceAction<InspectOutlayRecordItem> {

	@Resource
	IInspectOutlayRecordItemManager manager;

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "supplierInspectOutlayRecordItem_datasource" };
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(InspectOutlayRecordItem item) {
		manager.addInspectOutlayRecordItem(item);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<InspectOutlayRecordItem> getList(int startRow, int endRow) {
		return manager.queryInspectOutlayRecordItemList(startRow, endRow);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public InspectOutlayRecordItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateInspectOutlayRecordItem(newValues, itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteInspectOutlayRecordItem(itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<InspectOutlayRecordItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String inspectOutLayRecordId = (String) values
					.get("inspectOutLayRecordId");
			return manager.queryInspectOutlayRecordItemListByRecordId(startRow,
					endRow, inspectOutLayRecordId);
		}
		return null;
	}

}
