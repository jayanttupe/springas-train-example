package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BusinessScopeAccountBookManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.BusinessScopeAccountBook;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：经营范围电子账册相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BusinessScopeAccountBookDatasourceAction implements DataSourceAction<BusinessScopeAccountBook>{
	@Autowired
	private BusinessScopeAccountBookManager businessScopeAccountBookManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"businessScopeAccountBook_dataSource"};
	}

	/**
	 * 新增经营范围电子账册相关信息
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BusinessScopeAccountBook businessScopeAccountBook) {
		List<BusinessScopeAccountBookItems> newBusinessScopeAccountBookItemsList = OneToManyTools.ConvertToEntity(businessScopeAccountBook.getBusinessScopeAccountBookItems(), BusinessScopeAccountBookItems.class);
		businessScopeAccountBook.setBusinessScopeAccountBookItems(newBusinessScopeAccountBookItemsList);
		
		businessScopeAccountBookManager.saveBusinessScopeAccountBook(businessScopeAccountBook);
	}

	/**
	 * 更新经营范围电子账册相关信息
	 * @param newValues
	 * @param number
	 * @return 经营范围电子账册相关信息
	 */
	@Override
	public BusinessScopeAccountBook update(Map newValues, String number) {
		BusinessScopeAccountBook businessScopeAccountBook = new BusinessScopeAccountBook();		
		BeanPropUtil.fillEntityWithMap(businessScopeAccountBook, newValues);
		
		List<BusinessScopeAccountBookItems> newBusinessScopeAccountBookItemsList = OneToManyTools.ConvertToEntity(businessScopeAccountBook.getBusinessScopeAccountBookItems(), BusinessScopeAccountBookItems.class);
		businessScopeAccountBook.setBusinessScopeAccountBookItems(newBusinessScopeAccountBookItemsList);
		
		return businessScopeAccountBookManager.saveBusinessScopeAccountBook(businessScopeAccountBook);
	}

	/**
	 * 删除经营范围电子账册相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		businessScopeAccountBookManager.deleteEntity(number, BusinessScopeAccountBook.class);
	}

	/**
	 * 查询经营范围电子账册相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册相关信息
	 */
	@Override
	public List<BusinessScopeAccountBook> doQuery(Map values, int startRow, int endRow) {
		return businessScopeAccountBookManager.getBusinessScopeAccountBook(values, 0, -1);
	}

	/**
	 * 获取所有经营范围电子账册信息
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册信息
	 */
	@Override
	public List<BusinessScopeAccountBook> getList(int startRow, int endRow) {
		return businessScopeAccountBookManager.getBusinessScopeAccountBook(null, 0, -1);
	}
}