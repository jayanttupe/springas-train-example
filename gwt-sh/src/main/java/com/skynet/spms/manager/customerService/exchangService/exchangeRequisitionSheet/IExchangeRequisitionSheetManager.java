package com.skynet.spms.manager.customerService.exchangService.exchangeRequisitionSheet;
import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.customerService.sales.exchangeRequisitionSheet.ExchangeRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;

/**
 * 交换申请单业务接口
 * @author  lining
 *
 */
public interface IExchangeRequisitionSheetManager {
	
	
	public void addExchangeRequisitionSheet(ExchangeRequisitionSheet o);

	public ExchangeRequisitionSheet updateExchangeRequisitionSheet(Map<String, Object> newValues, String itemID);

	public void deleteExchangeRequisitionSheet(String itemID);

	public List<ExchangeRequisitionSheet> queryExchangeRequisitionSheetList(int startRow, int endRow);
	
	public ExchangeRequisitionSheet getExchangeRequisitionSheetById(String sheetId);
	
	/***
	 * 根据不同条件查询
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<ExchangeRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) ;

}
