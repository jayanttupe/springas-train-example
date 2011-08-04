package com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.CustomerPartRepairDisassembleData;
/**
 * 
 * 部件拆换数据业务接口
 *
 * @author   taiqichao
 * @version  
 * @Date	 2011	2011-7-11
 */
public interface CustomerPartRepairDisassembleDataManager {
	
	/**
	 *添加部件拆换信息 
	 *
	 * @param  @param o   部件拆换信息
	 * @return void  
	 */
	public void addCustomerPartRepairDisassembleData(CustomerPartRepairDisassembleData o);

	/**
	 * 
	 * 更新部件拆换信息 
	 *
	 * @param  @param newValues 客户端修改新值
	 * @param  @param itemID
	 * @param  @return   
	 * @return CustomerPartRepairDisassembleData   部件拆换信息
	 */
	public CustomerPartRepairDisassembleData updateCustomerPartRepairDisassembleData(
			Map<String, Object> newValues, String itemID);
	/**
	 * 
	 * 删除部件拆换信息 
	 *
	 * @param  @param itemID   部件编号
	 * @return void  
	 */
	public void deleteCustomerPartRepairDisassembleData(String itemID);

	/**
	 * 
	 *分页查询部件拆换信息
	 *
	 * @param  @param startRow 当前页索引
	 * @param  @param endRow 页大小
	 * @param  @return   
	 * @return List<CustomerPartRepairDisassembleData>  部件拆换信息集合
	 */
	public List<CustomerPartRepairDisassembleData> queryCustomerPartRepairDisassembleDataList(
			int startRow, int endRow);
	/**
	 * 
	 * 根据送修申请单编号查询备件拆换信息
	 *
	 * @param  @param sheetId 送修申请单编号
	 * @param  @return   
	 * @return CustomerPartRepairDisassembleData  备件拆换信息
	 */
	public CustomerPartRepairDisassembleData getCustomerPartRepairDisassembleDataById(
			String sheetId);
	/**
	 * 
	 * 根据拆换信息编号查询
	 *
	 * @param  @param itemID 编号
	 * @param  @return   
	 * @return List<CustomerPartRepairDisassembleData>  
	 * @throws
	 */
	public List<CustomerPartRepairDisassembleData> getCustomerPartRepairDisassembleDataByItemID(String itemID);
}
