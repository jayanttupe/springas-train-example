package com.skynet.spms.action.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.IProcurementQuotationSheetRecordItemManager;

/**
 *采购报价单明细Action实现类
 * 
 * @author 李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementQuotationSheetRecordItemAction implements
		DataSourceAction<ProcurementQuotationSheetRecordItem> {

	@Resource
	IProcurementQuotationSheetRecordItemManager manager;

	@Autowired
	private ListGridFilterManager<ProcurementQuotationSheetRecordItem> filterManager;

	public String[] getBindDsName() {
		return new String[] { "procurementQuotationSheetItem_datasource" };
	}

	/**
	 * 添加询价单明细
	 * 
	 * @param o
	 *            待添加的询价单明细
	 */
	public void insert(ProcurementQuotationSheetRecordItem item) {

		// 默认创建日期
		item.setCreateDate(new Date());

		if (item.getUnitPriceAmount() == null) {
			item.setUnitPriceAmount(0f);
		}
		if (item.getQuantity() == null) {
			item.setQuantity(0.0f);
		}
		if (item.getAmount() == null) {
			item.setAmount(0.0f);
		}
		if (item.getPackagePrice() == null) {
			item.setPackagePrice(0.0f);
		}
		manager.addProcurementQuotationSheetRecordItem(item);
	}

	/**
	 * 删除询价单明细
	 * 
	 * @param itemID
	 *            询价单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementQuotationSheetRecordItem(itemID);
	}

	/**
	 * 更新询价单明细
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            询价单明细Id
	 * @return 询价单明细对象
	 */
	public ProcurementQuotationSheetRecordItem update(
			Map<String, Object> newValues, String itemID) {
		return manager.updateProcurementQuotationSheetRecordItem(newValues,
				itemID);
	}

	/**
	 * 列表查询
	 * 
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数
	 * @return 询价单明细列表
	 */
	public List<ProcurementQuotationSheetRecordItem> getList(int startRow,
			int endRow) {
		return manager.queryProcurementQuotationSheetRecordItemList(startRow,
				endRow);
	}

	/**
	 * 列表筛选
	 * 
	 * @param values
	 *            筛选条件
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数
	 * @return 询价单明细列表
	 */
	public List<ProcurementQuotationSheetRecordItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {

		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(
					ProcurementQuotationSheetRecordItem.class, criteria,
					startRow, endRow);
		}

		List<ProcurementQuotationSheetRecordItem> items = new ArrayList<ProcurementQuotationSheetRecordItem>();
		String suppliersParityItemIds = values.get("suppliersParityItemIds")
				+ "";
		// 根据报价单id获得询价单明细信息
		if (values.get("suppliersParityItemIds") != null
				&& !"".equals(values.get("suppliersParityItemIds"))) {
			String[] itemIds = suppliersParityItemIds.split(",");
			ProcurementQuotationSheetRecordItem pqrItem = null;
			for (String id : itemIds) {
				pqrItem = manager
						.getProcurementQuotationSheetRecordItemById(id);
				if (pqrItem != null) {
					items.add(pqrItem);
				}

			}
			return items;
		}

		return manager.doQuery(values, startRow, endRow);
	}

}
