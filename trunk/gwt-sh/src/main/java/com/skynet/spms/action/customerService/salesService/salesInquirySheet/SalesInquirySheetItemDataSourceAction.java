package com.skynet.spms.action.customerService.salesService.salesInquirySheet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.salesInquirySheet.SalesInquirySheetItemManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;

/**
 * 询价单明细Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesInquirySheetItemDataSourceAction implements
		DataSourceAction<SalesInquirySheetItem> {

	@Autowired
	private SalesInquirySheetItemManager salesInquirySheetItemManager;

	
	@Autowired
	private ListGridFilterManager<SalesInquirySheetItem> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "salesInquirySheetItem_dataSource","doQuotationItem_dataSource" };
	}

	/**
	 * 添加询价单明细
	 * @param o 待添加的询价单
	 */
	public void insert(SalesInquirySheetItem item) {
		
		//默认报价状态为 未报价
		QuotationStatusEntity qse = new QuotationStatusEntity();
		qse.setM_QuotationStatus(QuotationStatus.didNotOffer);
		item.setM_QuotationStatusEntity(qse);

		//默认创建日期
		item.setCreateDate(new Date());
		salesInquirySheetItemManager.addSalesInquirySheetItem(item);
	}
	
	/**
	 * 删除询价单明细
	 * @param itemID 询价单明细id
	 */
	public void delete(String itemID) {
		this.salesInquirySheetItemManager.deleteSalesInquirySheetItem(itemID);
	}
	
	/**
	 * 修改询价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 询价单明细Id
	 * @return 询价单明细对象
	 */
	public SalesInquirySheetItem update(Map<String, Object> newValues,
			String itemID) {
		return salesInquirySheetItemManager.updateSalesInquirySheetItem(
				newValues, itemID);
	}

	

	/***
	 * 查询询价单明细
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 询价单明细列表
	 */
	public List<SalesInquirySheetItem> getList(int startRow, int endRow) {
		List<SalesInquirySheetItem> items = salesInquirySheetItemManager
				.querySalesInquirySheetItemAll();
		return items;
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单明细列表
	 */
	public List<SalesInquirySheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesInquirySheetItem.class,
					criteria, startRow, endRow);
		}
		
		List<SalesInquirySheetItem> items;
		//根据询价单明细id获得询价单明细信息
		if (values.get("id")!=null) {
			items = salesInquirySheetItemManager
					.querySalesInquirySheetItemListByInquiryId(values.get(
							"id").toString());
			return items;
		}
		//根据客户id查询询价单明细
		else if (values.get("customerId")!=null) {
			items = salesInquirySheetItemManager
					.querySalesInquirySheetItemListByCustomerId(values.get(
					"customerId").toString());
			return items;
		}
		return null;
	}



}
