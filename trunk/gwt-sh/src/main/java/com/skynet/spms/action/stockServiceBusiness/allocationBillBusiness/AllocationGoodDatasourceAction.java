package com.skynet.spms.action.stockServiceBusiness.allocationBillBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.AllocationGoodManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationGood;

/**
 * 描述：调拨单明细相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class AllocationGoodDatasourceAction implements DataSourceAction<AllocationGood>{
	@Autowired
	private AllocationGoodManager allocationGoodManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"allocationGood_dataSource"};
	}

	/**
	 * 新增调拨单明细相关信息
	 * @param allocationGood
	 */
	@Override
	public void insert(AllocationGood allocationGood) {
		allocationGoodManager.insertAllocationGood(allocationGood);
	}

	/**
	 * 更新调拨单明细相关信息
	 * @param newValues
	 * @param number
	 * @return 调拨单明细相关信息
	 */
	@Override
	public AllocationGood update(Map newValues, String number) {
		return (AllocationGood) allocationGoodManager.updateEntity(newValues, number, AllocationGood.class);
	}

	/**
	 * 删除调拨单明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		allocationGoodManager.deleteEntity(number, AllocationGood.class);
	}

	/**
	 * 查询相关调拨单明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 调拨单明细相关信息
	 */
	@Override
	public List<AllocationGood> doQuery(Map values, int startRow, int endRow) {
		return allocationGoodManager.getAllocationGood(values, startRow, endRow);
	}

	/**
	 * 获取所有调拨单明细信息
	 * @param startRow
	 * @param endRow
	 * @return 调拨单明细信息
	 */
	@Override
	public List<AllocationGood> getList(int startRow, int endRow) {
		return allocationGoodManager.list(0, -1, AllocationGood.class);
	}
}