package com.skynet.spms.action.supplierSupport.procurement.priceComparisonsReport;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.PriceComparisonsReport.IPriceComparisonsSheetReportManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.priceComparisonsReport.PriceComparisonsSheetReport;

/**
 * 采购比价但
 * 
 * @author lining
 * 
 */
@Component
public class PriceComparisonsSheetReportAction implements
		DataSourceAction<PriceComparisonsSheetReport> {

	@Resource
	IPriceComparisonsSheetReportManager manager;

	public String[] getBindDsName() {
		return new String[] { "PriceComparisonsSheetReport_datasource" };
	}

	public void insert(PriceComparisonsSheetReport item) {
		manager.addPriceComparisonsSheetReport(item);
	}

	public List<PriceComparisonsSheetReport> getList(int startRow, int endRow) {
		return manager.queryPriceComparisonsSheetReportList(startRow, endRow);
	}

	public PriceComparisonsSheetReport update(Map<String, Object> newValues,
			String itemID) {
		return manager.updatePriceComparisonsSheetReport(newValues, itemID);
	}

	public void delete(String itemID) {
		manager.deletePriceComparisonsSheetReport(itemID);
	}

	public List<PriceComparisonsSheetReport> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		return manager.queryPriceComparisonsSheetReportList(startRow, endRow);
	}

}
