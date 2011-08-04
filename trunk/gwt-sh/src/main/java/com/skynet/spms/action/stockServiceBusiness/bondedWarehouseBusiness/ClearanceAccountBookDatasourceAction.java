package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.ClearanceAccountBookManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.ClearanceAccountBook;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：通关电子帐册相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ClearanceAccountBookDatasourceAction implements DataSourceAction<ClearanceAccountBook>{
	@Autowired
	private ClearanceAccountBookManager clearanceAccountBookManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"clearanceAccountBook_dataSource"};
	}

	/**
	 * 新增通关电子帐册相关信息
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(ClearanceAccountBook businessScopeAccountBook) {
		List<ClearanceAccountBookItems> newClearanceAccountBookItemsList = OneToManyTools.ConvertToEntity(businessScopeAccountBook.getClearanceAccountBookItemsList(), ClearanceAccountBookItems.class);
		businessScopeAccountBook.setClearanceAccountBookItemsList(newClearanceAccountBookItemsList);
		
		clearanceAccountBookManager.saveClearanceAccountBook(businessScopeAccountBook);
	}

	/**
	 * 更新通关电子帐册相关信息
	 * @param newValues
	 * @param number
	 * @return 通关电子帐册相关信息
	 */
	@Override
	public ClearanceAccountBook update(Map newValues, String number) {
		ClearanceAccountBook businessScopeAccountBook = new ClearanceAccountBook();		
		BeanPropUtil.fillEntityWithMap(businessScopeAccountBook, newValues);
		
		List<ClearanceAccountBookItems> newClearanceAccountBookItemsList = OneToManyTools.ConvertToEntity(businessScopeAccountBook.getClearanceAccountBookItemsList(), ClearanceAccountBookItems.class);
		businessScopeAccountBook.setClearanceAccountBookItemsList(newClearanceAccountBookItemsList);
		
		return clearanceAccountBookManager.saveClearanceAccountBook(businessScopeAccountBook);
	}

	/**
	 * 删除通关电子帐册相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		clearanceAccountBookManager.deleteEntity(number, ClearanceAccountBook.class);
	}

	/**
	 * 查询通关电子帐册相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册相关信息
	 */
	@Override
	public List<ClearanceAccountBook> doQuery(Map values, int startRow, int endRow) {
		return clearanceAccountBookManager.getClearanceAccountBook(values, 0, -1);
	}

	/**
	 * 获取所有通关电子帐册信息
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册信息
	 */
	@Override
	public List<ClearanceAccountBook> getList(int startRow, int endRow) {
		return clearanceAccountBookManager.getClearanceAccountBook(null, 0, -1);
	}
}