package com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;

/**
 * 采购报价明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
public interface IProcurementQuotationSheetRecordItemManager {

	/**
	 * 添加报价单明细
	 * @param o 待添加的报价单明细
	 */
	public void addProcurementQuotationSheetRecordItem(
			ProcurementQuotationSheetRecordItem o);

	/**
	 * 更新报价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 报价单明细Id
	 * @return 报价单明细对象
	 */
	public ProcurementQuotationSheetRecordItem updateProcurementQuotationSheetRecordItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除报价单明细
	 * @param itemID 报价单明细id
	 */
	public void deleteProcurementQuotationSheetRecordItem(String itemID);

	/**
	 * 分页查询报价单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单明细列表
	 */
	public List<ProcurementQuotationSheetRecordItem> queryProcurementQuotationSheetRecordItemList(
			int startRow, int endRow);

	/**
	 * 根据报价单明细Id 获得报价单明细
	 * @param sheetId 报价单ID
	 * @return 报价单明细
	 */
	public ProcurementQuotationSheetRecordItem getProcurementQuotationSheetRecordItemById(
			String sheetId);
	
	/**
	 * 根据报价单明细Id获得报价单明细信息
	 * @param quotationId 报价单明细id
	 * @return 报价单明细列表
	 */
	public List<ProcurementQuotationSheetRecordItem> queryProcurementQuotationSheetItemListByQuotationId(String quotationId);

	
	/***
	 * 根据不同条件查询
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<ProcurementQuotationSheetRecordItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) ;
}
