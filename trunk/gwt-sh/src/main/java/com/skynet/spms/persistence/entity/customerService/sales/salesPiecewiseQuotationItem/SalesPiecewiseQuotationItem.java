package com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem.baseQuantityPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;
/**
 * 销售分段报价实体
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:04
 */
@Entity
@Table(name = "SPMS_SALES_PIECEWISEQITEM")
public class SalesPiecewiseQuotationItem extends baseQuantityPiecewiseQuotationItem {
	/**销售报价单明细**/
	private SalesQuotationSheetItem salesQuotationSheetItem;
	
	private List<SalesPiecewiseQuotationItem> m_SalesPiecewiseQuotationItem;
	
	@ManyToOne
	@JoinColumn(name = "SALES_QUOTATION_ITEM_ID")
	public SalesQuotationSheetItem getSalesQuotationSheetItem() {
		return salesQuotationSheetItem;
	}
	public void setSalesQuotationSheetItem(
			SalesQuotationSheetItem salesQuotationSheetItem) {
		this.salesQuotationSheetItem = salesQuotationSheetItem;
	}
	
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "BQPIECEWISEQITEM_ID")
	public List<SalesPiecewiseQuotationItem> getM_SalesPiecewiseQuotationItem() {
		return m_SalesPiecewiseQuotationItem;
	}
	public void setM_SalesPiecewiseQuotationItem(
			List<SalesPiecewiseQuotationItem> salesPiecewiseQuotationItem) {
		m_SalesPiecewiseQuotationItem = salesPiecewiseQuotationItem;
	}
	
}