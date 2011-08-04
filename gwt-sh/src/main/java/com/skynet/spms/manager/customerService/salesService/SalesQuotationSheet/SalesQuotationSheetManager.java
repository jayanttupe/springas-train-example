package com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;

/**
 * 销售报价Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesQuotationSheetManager {

	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void addSalesQuotationSheet(SalesQuotationSheet o);

	/**
	 * 修改报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public SalesQuotationSheet updateSalesQuotationSheet(Map<String, Object> newValues, String itemID);

	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void deleteSalesQuotationSheet(String itemID);

	/**
	 * 分页查询报价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<SalesQuotationSheet> querySalesQuotationSheetList(int startRow, int endRow);
	
	/**
	 * 根据报价单Id 获得报价单
	 * @param sheetId 报价打ID
	 * @return 报价单
	 */
	public SalesQuotationSheet getSalesQuotationSheetById(String sheetId);

	
	/***
	 * 根据不同条件查询
	 * @param values 参数
	 * @param startRow 开始行
	 * @param endRow 结束行
	 * @return 报价单列表
	 */
	public List<SalesQuotationSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) ;
	
	public void updateBussinessState(String className, String id, String state) ;
}
