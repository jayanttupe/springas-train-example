package com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;

/**
 * 销售报价明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
public interface SalesQuotationSheetItemManager {

	/**
	 * 添加报价单明细
	 * @param o 待添加的报价单明细
	 */
	public void addSalesQuotationSheetItem(SalesQuotationSheetItem o);

	/**
	 * 修改报价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 报价单明细Id
	 * @return 报价单明细对象
	 */
	public SalesQuotationSheetItem updateSalesQuotationSheetItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除报价单明细
	 * @param itemID 报价单明细id
	 */
	public void deleteSalesQuotationSheetItem(String itemID);

	/**
	 * 根据报价单id获得报价单明细
	 * @param QuotationId 报价单明细id
	 * @return 报价单明细列表
	 */
	public List<SalesQuotationSheetItem> querySalesQuotationSheetItemListByQuotationId(String QuotationId);

}
