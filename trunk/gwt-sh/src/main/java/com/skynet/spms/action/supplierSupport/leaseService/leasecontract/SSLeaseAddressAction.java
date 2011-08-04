package com.skynet.spms.action.supplierSupport.leaseService.leasecontract;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.lease.LeaseContract.SSLeaseAddressManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 地址数据源
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 */
@Component
public class SSLeaseAddressAction implements DataSourceAction<AddressInfo> {

	@Autowired
	private SSLeaseAddressManager manager;

	public String[] getBindDsName() {
		return new String[] { DSKey.S_LEASEADDRESS_DS };
	}

	/**
	 * 添加供应商租赁合同地址的方法
	 */
	public void insert(AddressInfo item) {
		manager.addAddress(item);
	}

	/**
	 * 分页查询供应商租赁合同地址的方法
	 */
	public List<AddressInfo> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	/**
	 * 修改供应商租赁合同地址的方法
	 */
	public AddressInfo update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}

	/**
	 * 删除供应商租赁合同地址的方法
	 */
	public void delete(String itemID) {
		manager.delete(itemID);
	}

	/**
	 * 根据条件查询供应商租赁合同地址的方法
	 */
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
