package com.skynet.spms.manager.customerService.RepairService.contract;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerInspectOutlayRecordItem;

/**
 * 送修记录明细业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface CusInspectOutlayRecordItemManager {
	/**
	 * 添加送修记录明细
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addInspectOutlayRecordItem(CustomerInspectOutlayRecordItem o);

	/**
	 * 更新送修记录明细
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerInspectOutlayRecordItem
	 */
	public CustomerInspectOutlayRecordItem updateInspectOutlayRecordItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除送修记录明细
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecordItem(String itemID);

	/**
	 * 分页查询送修记录明细
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<CustomerInspectOutlayRecordItem>
	 */
	public List<CustomerInspectOutlayRecordItem> queryInspectOutlayRecordItemList(
			int startRow, int endRow);

	/**
	 * 根据送修记录编号查询送修记录明细
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerInspectOutlayRecordItem
	 */
	public CustomerInspectOutlayRecordItem getInspectOutlayRecordItemById(
			String sheetId);

	/**
	 * 根据送修记录编号分页查询送修记录明细
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @param recordId
	 * @param @return
	 * @return List<CustomerInspectOutlayRecordItem>
	 */
	public List<CustomerInspectOutlayRecordItem> queryInspectOutlayRecordItemListByRecordId(
			int startRow, int endRow, String recordId);

}
