package com.skynet.spms.action.stockServiceBusiness.allocationBillBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.AllocationBillBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationBillBusiness;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationGood;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：调拨单相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
@SuppressWarnings("unchecked")
public class AllocationBillBusinessDatasourceAction implements DataSourceAction<AllocationBillBusiness>{
	@Autowired
	private AllocationBillBusinessManager allocationBillBusinessManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"allocationBillBusiness_dataSource"};
	}

	/**
	 * 新增调拨单相关信息
	 * @param allocationBillBusiness
	 */
	@Override
	public void insert(AllocationBillBusiness allocationBillBusiness) {
		List<AllocationGood> newAllocationGoodList = OneToManyTools.ConvertToEntity(allocationBillBusiness.getAllocationGood(), AllocationGood.class);
		allocationBillBusiness.setAllocationGood(newAllocationGoodList);
		
		allocationBillBusinessManager.saveAllocationBillBusiness(allocationBillBusiness);
	}

	/**
	 * 更新调拨单相关信息
	 * @param newValues
	 * @param number
	 * @return 调拨单相关信息
	 */
	@Override
	public AllocationBillBusiness update(Map newValues, String number) {
		AllocationBillBusiness allocationBillBusiness = new AllocationBillBusiness();		
		BeanPropUtil.fillEntityWithMap(allocationBillBusiness, newValues);
		
		List<AllocationGood> newAllocationGoodList = OneToManyTools.ConvertToEntity(allocationBillBusiness.getAllocationGood(), AllocationGood.class);
		allocationBillBusiness.setAllocationGood(newAllocationGoodList);
		
		return allocationBillBusinessManager.saveAllocationBillBusiness(allocationBillBusiness);
	}

	/**
	 * 删除相应调拨单信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		allocationBillBusinessManager.deleteEntity(number, AllocationBillBusiness.class);
	}

	/**
	 * 查询相关调拨单信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 调拨单相关信息
	 */
	@Override
	public List<AllocationBillBusiness> doQuery(Map values, int startRow, int endRow) {
		return allocationBillBusinessManager.getAllocationBillBusiness(values, 0, -1);
	}

	/**
	 * 获取所有调拨单信息
	 * @param startRow
	 * @param endRow
	 * @return 调拨单信息
	 */
	@Override
	public List<AllocationBillBusiness> getList(int startRow, int endRow) {
		return allocationBillBusinessManager.getAllocationBillBusiness(null, 0, -1);
	}
}