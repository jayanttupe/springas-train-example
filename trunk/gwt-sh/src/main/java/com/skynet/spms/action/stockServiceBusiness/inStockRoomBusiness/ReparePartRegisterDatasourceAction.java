/**
 * 
 */
package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReparePartRegisterManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.reparePartRegister.ReparePartRegister;

/**
 * 描述：送修单登记相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ReparePartRegisterDatasourceAction implements DataSourceAction<ReparePartRegister>{
	@Autowired
	private ReparePartRegisterManager reparePartRegisterManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"reparePartRegister_dataSource"};
	}
	
	/**
	 * 新增送修单登记相关信息
	 * @param reparePartRegisterBusiness
	 */
	@Override
	public void insert(ReparePartRegister reparePartRegisterBusiness) {
		reparePartRegisterManager.saveReparePartRegister(reparePartRegisterBusiness);
	}
	
	/**
	 * 更新送修单登记相关信息
	 * @param newValues
	 * @param number
	 * @return 送修单登记相关信息
	 */
	@Override
	public ReparePartRegister update(Map newValues, String number) {
		ReparePartRegister reparePartRegister = new ReparePartRegister();
		BeanPropUtil.fillEntityWithMap(reparePartRegister, newValues);

		return reparePartRegisterManager.saveReparePartRegister(reparePartRegister);
	}
	
	/**
	 * 删除送修单登记相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		reparePartRegisterManager.deleteEntity(number, ReparePartRegister.class);
	}
	
	/**
	 * 查询送修单登记相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 送修单登记相关信息
	 */
	@Override
	public List<ReparePartRegister> doQuery(Map values, int startRow, int endRow) {
		return reparePartRegisterManager.getReparePartRegisterBusiness(values, 0, -1);
	}
	
	/**
	 * 获取所有送修单登记信息
	 * @param startRow
	 * @param endRow
	 * @return 送修单登记信息
	 */
	@Override
	public List<ReparePartRegister> getList(int startRow, int endRow) {
		return reparePartRegisterManager.getReparePartRegisterBusiness(null, 0, -1);
	}
}