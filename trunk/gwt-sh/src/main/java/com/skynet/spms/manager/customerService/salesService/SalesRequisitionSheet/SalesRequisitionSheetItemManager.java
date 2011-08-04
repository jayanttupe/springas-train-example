package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;

/**
 * 销售订单明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesRequisitionSheetItemManager {
	/**
	 * 添加订单明细
	 * @param o 待添加的订单明细
	 */
	public void addSalesRequisitionSheetItem(SalesRequisitionSheetItem o);

	/**
	 * 修改订单明细
	 * @param newValues 新的数据对象
	 * @param itemID 订单明细Id
	 * @return 订单明细对象
	 */
	public SalesRequisitionSheetItem updateSalesRequisitionSheetItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除订单明细
	 * @param itemID 订单明细id
	 */
	public void deleteSalesRequisitionSheetItem(String itemID);

	/**
	 * 分页查询订单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单明细列表
	 */
	public List<SalesRequisitionSheetItem> querySalesRequisitionSheetItemListByRequisitionId(String RequisitionId);
}
