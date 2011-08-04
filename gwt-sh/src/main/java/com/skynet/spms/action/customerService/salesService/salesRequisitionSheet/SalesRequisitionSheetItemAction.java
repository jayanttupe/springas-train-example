package com.skynet.spms.action.customerService.salesService.salesRequisitionSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetItemManager;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.RequisitionStatusEntity;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.RequisitionStatus;
/**
 * 客户订单明细
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesRequisitionSheetItemAction implements
		DataSourceAction<SalesRequisitionSheetItem> {

	@Autowired
	private SalesRequisitionSheetItemManager salesRequisitionSheetItemManager;
	
	@Autowired
	private SalesRequisitionSheetManager salesRequisitionSheetManager;
	
	@Autowired
	private ListGridFilterManager<SalesRequisitionSheetItem> filterManager;
	
	/** 提供客户订单明细数据源**/
	public String[] getBindDsName() {
		return new String[] {DSKey.C_SALESREQUISITIONSHEET_ITEM_DS};
	}

	/**
	 * 添加订单明细
	 * @param o 待添加的订单
	 */
	public void insert(SalesRequisitionSheetItem item) {
		//
		RequisitionStatusEntity rse = new RequisitionStatusEntity();
		rse.setM_RequisitionStatus(RequisitionStatus.didNotOffer);
		// item.setM_RequisitionStatusEntity(rse);

		// 默认创建日期
		item.setCreateDate(new Date());
		
		
		salesRequisitionSheetItemManager.addSalesRequisitionSheetItem(item);
	}
	
	/**
	 * 删除订单明细
	 * @param itemID 订单明细id
	 */
	public void delete(String itemID) {
		this.salesRequisitionSheetItemManager
				.deleteSalesRequisitionSheetItem(itemID);
	}
	
	/**
	 * 修改订单明细
	 * @param newValues 新的数据对象
	 * @param itemID 订单明细Id
	 * @return 订单明细对象
	 */
	public SalesRequisitionSheetItem update(Map<String, Object> newValues,
			String itemID) {
		return salesRequisitionSheetItemManager
				.updateSalesRequisitionSheetItem(newValues, itemID);
	}

	
	/***
	 * 查询订单明细
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 订单明细列表
	 */
	public List<SalesRequisitionSheetItem> getList(int startRow, int endRow) {
		List<SalesRequisitionSheetItem> items = new ArrayList<SalesRequisitionSheetItem>();
		return items;
	}

	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单明细列表
	 */
	public List<SalesRequisitionSheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesRequisitionSheetItem.class,
					criteria, startRow, endRow);
		}
		
		List<SalesRequisitionSheetItem> items = new ArrayList<SalesRequisitionSheetItem>();
		
		Object requisitionSheetNumberObj=values.get("requisitionSheetNumber");
		if(requisitionSheetNumberObj!=null&&StringUtils.isNotBlank(requisitionSheetNumberObj+"")){
			SalesRequisitionSheet requistionSheet	=salesRequisitionSheetManager.getSalesRequisitionSheetByNumber(requisitionSheetNumberObj+"");
			items = salesRequisitionSheetItemManager
			.querySalesRequisitionSheetItemListByRequisitionId(requistionSheet.getId());
		}
		
		// 根据id获得报价单明细信息
		Object obj=values.get("id");
		if (obj!=null&&StringUtils.isNotBlank(obj.toString())) {
			items = salesRequisitionSheetItemManager
					.querySalesRequisitionSheetItemListByRequisitionId(values
							.get("id").toString());
		}
		return items;
	}

	

}
