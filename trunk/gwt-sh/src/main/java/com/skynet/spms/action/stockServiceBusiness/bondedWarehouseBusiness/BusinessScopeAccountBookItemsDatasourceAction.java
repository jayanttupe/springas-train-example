package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BusinessScopeAccountBookItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;

/**
 * 描述：经营范围电子账册明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BusinessScopeAccountBookItemsDatasourceAction implements DataSourceAction<BusinessScopeAccountBookItems>{
	@Autowired
	private BusinessScopeAccountBookItemsManager businessScopeAccountBookItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"businessScopeAccountBookItems_dataSource"};
	}

	/**
	 * 新增经营范围电子账册明细相关信息
	 * @param businessScopeAccountBookItems
	 */
	@Override
	public void insert(BusinessScopeAccountBookItems businessScopeAccountBookItems) {
		businessScopeAccountBookItemsManager.insertBusinessScopeAccountBookItems(businessScopeAccountBookItems);
		}

	/**
	 * 更新经营范围电子账册明细相关信息
	 * @param newValues
	 * @param number
	 * @return 经营范围电子账册明细相关信息
	 */
	@Override
	public BusinessScopeAccountBookItems update(Map newValues, String number) {
		return (BusinessScopeAccountBookItems) businessScopeAccountBookItemsManager.updateEntity(newValues, number, BusinessScopeAccountBookItems.class);
		}

	/**
	 * 删除经营范围电子账册明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		businessScopeAccountBookItemsManager.deleteEntity(number, BusinessScopeAccountBookItems.class);
	}

	/**
	 * 查询经营范围电子账册明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册明细相关信息
	 */
	@Override
	public List<BusinessScopeAccountBookItems> doQuery(Map values, int startRow, int endRow) {
		return businessScopeAccountBookItemsManager.getBusinessScopeAccountBookItems(values, startRow, endRow);
	}

	/**
	 * 获取所有经营范围电子账册明细信息
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册明细信息
	 */
	@Override
	public List<BusinessScopeAccountBookItems> getList(int startRow, int endRow) {
		return businessScopeAccountBookItemsManager.list(0, -1, BusinessScopeAccountBookItems.class);
	}
}