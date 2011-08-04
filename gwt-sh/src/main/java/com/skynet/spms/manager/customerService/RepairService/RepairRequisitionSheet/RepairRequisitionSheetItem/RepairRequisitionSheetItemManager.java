package com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItem;

/**
 * 送修申请单明细业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface RepairRequisitionSheetItemManager {
	/**
	 * 添加送修申请单明细
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addRepairRequisitionSheetItem(RepairRequisitionSheetItem o);

	/**
	 * 更新送修申请单明细
	 * 
	 * @param @param newValues 客户端修改值
	 * @param @param itemID 编号
	 * @param @return
	 * @return RepairRequisitionSheetItem
	 */
	public RepairRequisitionSheetItem updateRepairRequisitionSheetItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除送修申请明细
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairRequisitionSheetItem(String itemID);

	/**
	 * 分页查询送修申请单明细
	 * 
	 * @param @param startRow 当前页索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<RepairRequisitionSheetItem>
	 */
	public List<RepairRequisitionSheetItem> queryRepairRequisitionSheetItemList(
			int startRow, int endRow);

	/**
	 * 根据编号查询送修申请单明细
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairRequisitionSheetItem
	 */
	public RepairRequisitionSheetItem getRepairRequisitionSheetItemById(
			String sheetId);

}
