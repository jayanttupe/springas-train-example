package com.skynet.spms.action.customerService.repairService.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.RepairService.contract.CusInspectOutlayRecordItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerInspectOutlayRecordItem;

/**
 * 修理记录明细
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Component
public class CustomerInspectOutlayRecordItemAction implements
		DataSourceAction<CustomerInspectOutlayRecordItem> {

	@Resource
	CusInspectOutlayRecordItemManager manager;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "customerInspectOutlayRecordItem_datasource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(CustomerInspectOutlayRecordItem item) {
		manager.addInspectOutlayRecordItem(item);
	}

	/**
	 * 分页显示 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<CustomerInspectOutlayRecordItem> getList(int startRow,
			int endRow) {
		return new ArrayList<CustomerInspectOutlayRecordItem>();
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public CustomerInspectOutlayRecordItem update(
			Map<String, Object> newValues, String itemID) {
		return manager.updateInspectOutlayRecordItem(newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteInspectOutlayRecordItem(itemID);
	}

	/**
	 * 高级查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<CustomerInspectOutlayRecordItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String inspectOutLayRecordId = (String) values
					.get("inspectOutLayRecordId");
			return manager.queryInspectOutlayRecordItemListByRecordId(startRow,
					endRow, inspectOutLayRecordId);
		}
		return null;
	}

}
