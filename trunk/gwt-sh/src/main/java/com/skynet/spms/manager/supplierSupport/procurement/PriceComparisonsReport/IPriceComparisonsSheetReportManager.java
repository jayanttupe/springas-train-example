package com.skynet.spms.manager.supplierSupport.procurement.PriceComparisonsReport;
import java.util.List;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.priceComparisonsReport.PriceComparisonsSheetReport;
import java.util.Map;
/**
 *采购比价但
 * @author  lining
 *
 */
public interface IPriceComparisonsSheetReportManager {
	
	
	public void addPriceComparisonsSheetReport(PriceComparisonsSheetReport o);

	public PriceComparisonsSheetReport updatePriceComparisonsSheetReport(Map<String, Object> newValues, String itemID);

	public void deletePriceComparisonsSheetReport(String itemID);

	public List<PriceComparisonsSheetReport> queryPriceComparisonsSheetReportList(int startRow, int endRow);
	
	public PriceComparisonsSheetReport getPriceComparisonsSheetReportById(String sheetId);

}
