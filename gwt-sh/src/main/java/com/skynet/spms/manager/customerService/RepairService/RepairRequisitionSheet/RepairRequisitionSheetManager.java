package com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheet;

/**
 * 送修申请单业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface RepairRequisitionSheetManager {
	/**
	 * 添加送修申请单
	 * 
	 * @param @param o 送修申请单
	 * @return void
	 */
	public void addRepairRequisitionSheet(RepairRequisitionSheet o);

	/**
	 * 更新送修申请单
	 * 
	 * @param @param newValues 客户端修改值
	 * @param @param itemID
	 * @param @return
	 * @return RepairRequisitionSheet
	 */
	public RepairRequisitionSheet updateRepairRequisitionSheet(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除送修申请单
	 * 
	 * @param @param itemID 修申请单编号
	 * @return void
	 */
	public void deleteRepairRequisitionSheet(String itemID);

	/**
	 * 分页显示送修申请单
	 * 
	 * @param @param startRow 当前页索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<RepairRequisitionSheet>
	 */
	public List<RepairRequisitionSheet> queryRepairRequisitionSheetList(
			int startRow, int endRow);

	/**
	 * 根据编号查询送修申请单
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairRequisitionSheet
	 */
	public RepairRequisitionSheet getRepairRequisitionSheetById(String sheetId);

}
