package com.skynet.spms.action.supplierSupport.procurement.procurementInquirySheet;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.IProcurementInquirySheetItemManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheetItem;

/**
 * 采购询价单明细Action实现类
 * @author 李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementInquirySheetItemAction implements
		DataSourceAction<ProcurementInquirySheetItem> {

	@Resource
	IProcurementInquirySheetItemManager manager;

	@Autowired
	private ListGridFilterManager<ProcurementInquirySheetItem> filterManager;

	public String[] getBindDsName() {
		return new String[] { "procurementInquirySheetItem_datasource" };
	}

	/**
	 * 添加询价单明细
	 * 
	 * @param o
	 *            待添加的询价单明细
	 */
	public void insert(ProcurementInquirySheetItem item) {
		// 默认报价状态为 未报价
		QuotationStatusEntity qse = new QuotationStatusEntity();
		qse.setM_QuotationStatus(QuotationStatus.didNotOffer);
		item.setM_QuotationStatusEntity(qse);

		// 默认创建日期
		item.setCreateDate(new Date());
		manager.addProcurementInquirySheetItem(item);
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
	public List<ProcurementInquirySheetItem> getList(int startRow, int endRow) {
		return manager.queryProcurementInquirySheetItemList(startRow, endRow);
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
	public ProcurementInquirySheetItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementInquirySheetItem(newValues, itemID);
	}

	/**
	 * 删除询价单明细
	 * 
	 * @param itemID
	 *            询价单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementInquirySheetItem(itemID);
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
	public List<ProcurementInquirySheetItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {

		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(
					ProcurementInquirySheetItem.class, criteria, startRow,
					endRow);
		}

		List<ProcurementInquirySheetItem> items;
		// 根据询价单id获得询价单明细信息
		if (values.get("id") != null) {
			items = manager
					.queryProcurementInquirySheetItemListByInquiryId(values
							.get("id").toString());
			return items;
		}
		return manager.queryProcurementInquirySheetItemList(startRow, endRow);
	}

}
