package com.skynet.spms.manager.supplierSupport.lease.LeaseContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 供应商租赁合同地址接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface SSLeaseAddressManager {
	/**
	 * 
	 * 添加供应商租赁合同地址的方法
	 *
	 * @param  供应商租赁合同地址的对象
	 * @return void  
	 */
	public void addAddress(AddressInfo form);

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	public AddressInfo getById(String id);

	/**
	 * 
	 * 修改供应商租赁合同地址的方法
	 * 
	 * @param 新数据对象
	 * @param 供应商租赁合同地址的ID
	 * @return 供应商租赁合同地址对象
	 */
	public AddressInfo update(Map<String, Object> newValues, String itemID);

	/**
	 * 调用此方法是传入的values的key只能是rqId
	 * 
	 * @param 新数据对象
	 * @param 首页
	 * @param 尾页
	 * @return
	 */
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow);

	/**
	 * 分页查询
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 供应商租赁合同地址对象集合
	 */
	public List<AddressInfo> getList(int startRow, int endRow);

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param 要删除对象的ID
	 */
	public void delete(String itemID);

}
