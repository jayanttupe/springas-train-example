package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;

/**
 * 销售订单Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesRequisitionSheetManager {

	/**
	 * 添加订单
	 * @param o 待添加的订单
	 */
	public void addSalesRequisitionSheet(SalesRequisitionSheet o);

	/**
	 * 修改订单
	 * @param newValues 新的数据对象
	 * @param itemID 订单Id
	 * @return 订单对象
	 */
	public SalesRequisitionSheet updateSalesRequisitionSheet(Map<String, Object> newValues, String itemID);

	/**
	 * 删除订单
	 * @param itemID 订单id
	 */
	public void deleteSalesRequisitionSheet(String itemID);

	/**
	 * 分页查询订单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单列表
	 */
	public List<SalesRequisitionSheet> querySalesRequisitionSheetList(int startRow, int endRow);
	
	/**
	 * 根据订单Id 获得订单
	 * @param sheetId 订单ID
	 * @return 订单
	 */
	public SalesRequisitionSheet getSalesRequisitionSheetById(String sheetId);
	
	/**
	 * 根据订单编号 获得订单
	 * @param number 编号
	 * @return 订单
	 */
	public SalesRequisitionSheet getSalesRequisitionSheetByNumber(String number);
	
	/***
	 * 根据不同条件查询
	 * @param values 筛选条件
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return  订单列表
	 */
	public List<SalesRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) ;
}
