package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem.baseQuantityPiecewiseQuotationItem;

@Entity
@Table(name = "SPMS_PRO_PIECEWISEQITEM")
public class ProcurementPiecewiseQuotationItem extends baseQuantityPiecewiseQuotationItem {
	/**采购报价单明细**/
	private ProcurementQuotationSheetRecordItem procurementQuotationSheetRecordItem;
	
	@ManyToOne
	@JoinColumn(name = "PRO_QUOTATION_ITEM_ID")
	public ProcurementQuotationSheetRecordItem getProcurementQuotationSheetRecordItem() {
		return procurementQuotationSheetRecordItem;
	}
	public void setProcurementQuotationSheetRecordItem(
			ProcurementQuotationSheetRecordItem procurementQuotationSheetRecordItem) {
		this.procurementQuotationSheetRecordItem = procurementQuotationSheetRecordItem;
	}
}
