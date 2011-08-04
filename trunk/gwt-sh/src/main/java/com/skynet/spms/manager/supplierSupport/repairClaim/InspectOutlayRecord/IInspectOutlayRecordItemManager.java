package com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecordItem;

/**
 * 供应商检修记录明细业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IInspectOutlayRecordItemManager {
	/**
	 * 添加供应商检修记录明细
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addInspectOutlayRecordItem(InspectOutlayRecordItem o);

	/**
	 * 更新供应商检修记录明细
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return InspectOutlayRecordItem
	 */
	public InspectOutlayRecordItem updateInspectOutlayRecordItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商检修记录明细
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecordItem(String itemID);

	/**
	 * 分页查询供应商检修记录明细
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @return
	 * @return List<InspectOutlayRecordItem>
	 */
	public List<InspectOutlayRecordItem> queryInspectOutlayRecordItemList(
			int startRow, int endRow);

	/**
	 * 根据供应商检修记录明细编号查询
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return InspectOutlayRecordItem
	 */
	public InspectOutlayRecordItem getInspectOutlayRecordItemById(String sheetId);

	/**
	 * 根据供应商检修记录分页查询供应商检修记录明细
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @param recordId
	 * @param
	 * @return
	 * @return List<InspectOutlayRecordItem>
	 */
	public List<InspectOutlayRecordItem> queryInspectOutlayRecordItemListByRecordId(
			int startRow, int endRow, String recordId);

}
