package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.InStockApprovalRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord.InStockApprovalRecord;

/**
 * 描述：保税库入库记录相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class InStockApprovalRecordDatasourceAction implements DataSourceAction<InStockApprovalRecord>{
	@Autowired
	private InStockApprovalRecordManager inStockApprovalRecordManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"inStockApprovalRecord_dataSource"};
	}

	/**
	 * 新增保税库入库记录相关信息
	 * @param inStockApprovalRecord
	 */
	@Override
	public void insert(InStockApprovalRecord inStockApprovalRecord) {
		inStockApprovalRecordManager.insertEntity(inStockApprovalRecord);
	}

	/**
	 * 更新保税库入库记录相关信息
	 * @param newValues
	 * @param number
	 * @return 保税库入库记录相关信息
	 */
	@Override
	public InStockApprovalRecord update(Map newValues, String number) {
		return (InStockApprovalRecord) inStockApprovalRecordManager.updateEntity(newValues, number, InStockApprovalRecord.class);
	}

	/**
	 * 删除保税库入库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		inStockApprovalRecordManager.deleteEntity(number, InStockApprovalRecord.class);
	}

	/**
	 * 查询相关保税库入库记录信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录相关信息
	 */
	@Override
	public List<InStockApprovalRecord> doQuery(Map values, int startRow, int endRow) {
		return inStockApprovalRecordManager.getInStockApprovalRecord(values, 0, -1);
	}

	/**
	 * 获取所有保税库入库记录信息
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录信息
	 */
	@Override
	public List<InStockApprovalRecord> getList(int startRow, int endRow) {
		return inStockApprovalRecordManager.getInStockApprovalRecord(null, 0, -1);
	}
}