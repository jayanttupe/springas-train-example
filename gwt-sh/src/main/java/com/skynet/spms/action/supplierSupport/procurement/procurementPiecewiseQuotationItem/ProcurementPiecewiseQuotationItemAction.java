package com.skynet.spms.action.supplierSupport.procurement.procurementPiecewiseQuotationItem;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.procurementPiecewiseQuotationItem.IProcurementPiecewiseQuotationItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;

/**
 * 采购分段报价
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementPiecewiseQuotationItemAction implements
		DataSourceAction<ProcurementPiecewiseQuotationItem> {

	@Resource
	IProcurementPiecewiseQuotationItemManager manager;

	public String[] getBindDsName() {
		return new String[] { "ProcurementPiecewiseQuotationItem_datasource" };
	}
	
	
	public void insert(ProcurementPiecewiseQuotationItem item) {
		manager.addProcurementPiecewiseQuotationItem(item);
	}

	public List<ProcurementPiecewiseQuotationItem> getList(int startRow, int endRow) {
		return manager.queryProcurementPiecewiseQuotationItemList(startRow, endRow);
	}

	public ProcurementPiecewiseQuotationItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementPiecewiseQuotationItem(newValues, itemID);
	}

	public void delete(String itemID) {
		manager.deleteProcurementPiecewiseQuotationItem(itemID);
	}

	public List<ProcurementPiecewiseQuotationItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List<ProcurementPiecewiseQuotationItem> items;
		// 根据报价单id获得报价单明细信息
		if (StringUtils.isNotBlank(values.get("id").toString())) {
			items = manager.queryProcurementPiecewiseQuotationItemListByQuotationItemId(values
							.get("id").toString());
			return items;
		}

		return null;
	}

}
