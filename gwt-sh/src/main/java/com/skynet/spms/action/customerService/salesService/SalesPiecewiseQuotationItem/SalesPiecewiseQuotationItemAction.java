package com.skynet.spms.action.customerService.salesService.SalesPiecewiseQuotationItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.salesService.salesPiecewiseQuotationItem.ISalesPiecewiseQuotationItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;

/**
 * 销售分段报价Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesPiecewiseQuotationItemAction implements
		DataSourceAction<SalesPiecewiseQuotationItem> {

	@Resource
	ISalesPiecewiseQuotationItemManager manager;

	public String[] getBindDsName() {
		return new String[] { "SalesPiecewiseQuotationItem_datasource" };
	}

	/**
	 * 添加分段报价单
	 * @param o 待添加的分段报价单
	 */
	public void insert(SalesPiecewiseQuotationItem item) {
		manager.addSalesPiecewiseQuotationItem(item);
	}

	

	/**
	 * 修改分段报价单
	 * @param newValues 新的数据对象
	 * @param itemID 分段报价单Id
	 * @return 分段报价单对象
	 */
	public SalesPiecewiseQuotationItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateSalesPiecewiseQuotationItem(newValues, itemID);
	}

	
	/**
	 * 删除分段报价单
	 * @param itemID 分段报价单id
	 */
	public void delete(String itemID) {
		manager.deleteSalesPiecewiseQuotationItem(itemID);
	}

	/***
	 * 查询分段报价单
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 分段报价单列表
	 */
	public List<SalesPiecewiseQuotationItem> getList(int startRow, int endRow) {
		return manager.querySalesPiecewiseQuotationItemList(startRow, endRow);
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  分段报价单列表
	 */
	public List<SalesPiecewiseQuotationItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		List<SalesPiecewiseQuotationItem> items = new ArrayList<SalesPiecewiseQuotationItem>();
		// 根据报价单id获得报价单明细信息
		if (StringUtils.isNotBlank(values.get("id").toString())) {
			items = manager
					.querySalesPiecewiseQuotationItemListByQuotationItemId(values
							.get("id").toString());
			return items;
		}

		return items;
	}

}
