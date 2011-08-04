package com.skynet.spms.manager.customerService.salesService.salesPiecewiseQuotationItem;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;
/**
 * 销售分段报价Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface ISalesPiecewiseQuotationItemManager {
	
	/**
	 * 添加分段报价
	 * @param o 待添加的分段报价
	 */
	public void addSalesPiecewiseQuotationItem(SalesPiecewiseQuotationItem o);

	/**
	 * 更新分段报价
	 * @param newValues 新的数据对象
	 * @param itemID 分段报价Id
	 * @return 分段报价对象
	 */
	public SalesPiecewiseQuotationItem updateSalesPiecewiseQuotationItem(Map<String, Object> newValues, String itemID);

	/**
	 * 删除分段报价
	 * @param itemID 分段报价id
	 */
	public void deleteSalesPiecewiseQuotationItem(String itemID);

	/**
	 * 分页查询分段报价
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  分段报价列表
	 */
	public List<SalesPiecewiseQuotationItem> querySalesPiecewiseQuotationItemList(int startRow, int endRow);
	
	/**
	 * 根据分段报价明细Id 获得分段报价
	 * @param sheetId 分段报价Id
	 * @return 分段报价
	 */
	public SalesPiecewiseQuotationItem getSalesPiecewiseQuotationItemById(String sheetId);
	
	/**
	 * 根据报价单明细id 查询分段报价
	 * @param inquiryId 分段报价Id
	 * @return 分段报价
	 */
	public List<SalesPiecewiseQuotationItem> querySalesPiecewiseQuotationItemListByQuotationItemId(String quotationItemId);

}
