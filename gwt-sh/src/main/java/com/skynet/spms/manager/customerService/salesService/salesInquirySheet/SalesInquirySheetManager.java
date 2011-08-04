package com.skynet.spms.manager.customerService.salesService.salesInquirySheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;

/**
 *销售询价单Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesInquirySheetManager {

	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void addSalesInquirySheet(SalesInquirySheet o);

	/**
	 * 修改询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public SalesInquirySheet updateSalesInquirySheet(Map<String, Object> newValues, String itemID);

	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void deleteSalesInquirySheet(String itemID);

	/**
	 * 分页查询询价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<SalesInquirySheet> querySalesInquirySheetList(int startRow, int endRow);
	
	/**
	 * 根据询价单Id 获得询价单
	 * @param sheetId 询价打ID
	 * @return 询价单
	 */
	public SalesInquirySheet getSalesInquirySheetById(String sheetId);
	
	/***
	 * 根据用户id获得询价单
	 * @param customerId 客户id
	 * @return 询价单列表
	 */
	public List<SalesInquirySheet> querySalesInquirySheetListByCustomerId(
			String customerId) ;
	
	/***
	 * 根据用户名称获得询价单
	 * @param customerId 客户名称
	 * @return 询价单列表
	 */
	public List<SalesInquirySheet> querySalesInquirySheetListByCustomerName(
			String customerName) ;
	
	
	/***
	 * 根据不同条件查询
	 * @param values 参数
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 询价单列表
	 */
	public List<SalesInquirySheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) ;
}
