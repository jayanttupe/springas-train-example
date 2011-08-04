package com.skynet.spms.action.customerService.salesService.SalesQuotationSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.SalesQuotationSheetItemManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.RequisitionStatusEntity;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.RequisitionStatus;

/**
 * 询价单明细Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesQuotationSheetItemDataSourceAction implements
		DataSourceAction<SalesQuotationSheetItem> {

	@Autowired
	private SalesQuotationSheetItemManager salesQuotationSheetItemManager;

	@Autowired
	private ListGridFilterManager<SalesQuotationSheetItem> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "salesQuotationSheetItem_dataSource" };
	}
	
	/**
	 * 添加报价单明细
	 * @param o 待添加的报价单
	 */
	public void insert(SalesQuotationSheetItem item) {
		//
		RequisitionStatusEntity rse = new RequisitionStatusEntity();
		rse.setM_RequisitionStatus(RequisitionStatus.didNotOffer);
		//item.setM_RequisitionStatusEntity(rse);
		
		//默认创建日期
		item.setCreateDate(new Date());
		
		if(item.getUnitPriceAmount()==null){
			item.setUnitPriceAmount(0f);
		}
		if(item.getQuantity()==null){
			item.setQuantity(0.0f);
		}
		if(item.getAmount()==null){
			item.setAmount(0.0f);
		}
		if(item.getPackagePrice()==null){
			item.setPackagePrice(0.0f);
		}
		salesQuotationSheetItemManager.addSalesQuotationSheetItem(item);
	}
	
	
	/**
	 * 删除报价单明细
	 * @param itemID 报价单明细id
	 */
	public void delete(String itemID) {
		this.salesQuotationSheetItemManager.deleteSalesQuotationSheetItem(itemID);
	}
	
	/**
	 * 修改报价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 报价单明细Id
	 * @return 报价单明细对象
	 */
	public SalesQuotationSheetItem update(Map<String, Object> newValues,
			String itemID) {
		return salesQuotationSheetItemManager.updateSalesQuotationSheetItem(newValues, itemID);
	}

	/***
	 * 查询报价单明细
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 报价单明细列表
	 */
	public List<SalesQuotationSheetItem> getList(int startRow, int endRow) {
		List<SalesQuotationSheetItem> items = new ArrayList<SalesQuotationSheetItem>();
		return items;
	}

	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单明细列表
	 */
	public List<SalesQuotationSheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesQuotationSheetItem.class,
					criteria, startRow, endRow);
		}
		
		List<SalesQuotationSheetItem> items =new  ArrayList<SalesQuotationSheetItem>();
		//根据报价单id获得报价单明细信息
		if (values.get("id")!=null) {
			items = salesQuotationSheetItemManager
					.querySalesQuotationSheetItemListByQuotationId(values.get(
							"id").toString());
		}
		return items;
	}

	

}
