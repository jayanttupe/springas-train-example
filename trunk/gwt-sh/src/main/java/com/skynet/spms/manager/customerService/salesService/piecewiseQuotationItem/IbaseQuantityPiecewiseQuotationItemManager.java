package com.skynet.spms.manager.customerService.salesService.piecewiseQuotationItem;
import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem.baseQuantityPiecewiseQuotationItem;
public interface IbaseQuantityPiecewiseQuotationItemManager {
	
	
	public void addbaseQuantityPiecewiseQuotationItem(baseQuantityPiecewiseQuotationItem o);

	public baseQuantityPiecewiseQuotationItem updatebaseQuantityPiecewiseQuotationItem(Map<String, Object> newValues, String itemID);

	public void deletebaseQuantityPiecewiseQuotationItem(String itemID);

	public List<baseQuantityPiecewiseQuotationItem> querybaseQuantityPiecewiseQuotationItemList(int startRow, int endRow);
	
	public baseQuantityPiecewiseQuotationItem getbaseQuantityPiecewiseQuotationItemById(String sheetId);
	
	public List<baseQuantityPiecewiseQuotationItem> querybaseQuantityPiecewiseQuotationItemListByQuotationItemId(String QuotationItemId);
}
