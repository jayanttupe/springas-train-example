package com.skynet.spms.manager.customerService.salesService.salesInquirySheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;

/**
 * 销售收询价单明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesInquirySheetItemManager {

	/**
	 * 添加询价单明细
	 * @param o 待添加的询价单明细
	 */
	public void addSalesInquirySheetItem(SalesInquirySheetItem o);

	/**
	 * 修改询价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 询价单明细Id
	 * @return 询价单明细对象
	 */
	public SalesInquirySheetItem updateSalesInquirySheetItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除询价单明细
	 * @param itemID 询价单明细id
	 */
	public void deleteSalesInquirySheetItem(String itemID);

	/**
	 * 根据询价单Id 获得询价单明细
	 * @param sheetId 询价单明细Id
	 * @return 询价单明细
	 */
	public List<SalesInquirySheetItem> querySalesInquirySheetItemListByInquiryId(String inquiryId);
	
	/**
	 * 获得所有询价单明细
	 * @return 询价单明细
	 */
	public List<SalesInquirySheetItem> querySalesInquirySheetItemAll();
	
	/**
	 * 根据客户id获得 询价单信息
	 * @param customerId 客户id
	 * @return 询价单列表
	 */
	public List<SalesInquirySheetItem> querySalesInquirySheetItemListByCustomerId(
			String customerId) ;

}
