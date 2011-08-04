package com.skynet.spms.action.supplierSupport.repairClaim;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord.IRepairQuoteRecordManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecord;

/**
 * 修理记录
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
@Component
public class RepairQuoteRecordAction implements
		DataSourceAction<RepairQuoteRecord> {

	@Resource
	IRepairQuoteRecordManager manager;

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "supplierRepairQuteRecord_datasource" };
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(RepairQuoteRecord item) {
		manager.addRepairQuoteRecord(item);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<RepairQuoteRecord> getList(int startRow, int endRow) {
		return manager.queryRepairQuoteRecordList(startRow, endRow);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public RepairQuoteRecord update(Map<String, Object> newValues, String itemID) {
		return manager.updateRepairQuoteRecord(newValues, itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteRepairQuoteRecord(itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<RepairQuoteRecord> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String contractId = (String) values.get("contractId");
			return manager.getRepairQuoteRecordByContractID(contractId);
		}
		return manager.queryRepairQuoteRecordList(startRow, endRow);
	}

}
