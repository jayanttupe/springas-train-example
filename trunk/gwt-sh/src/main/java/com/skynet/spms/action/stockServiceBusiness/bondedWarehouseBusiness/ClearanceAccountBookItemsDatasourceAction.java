package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.ClearanceAccountBookItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;

/**
 * 描述：通关电子帐册明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ClearanceAccountBookItemsDatasourceAction implements DataSourceAction<ClearanceAccountBookItems>{
	@Autowired
	private ClearanceAccountBookItemsManager clearanceAccountBookItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"clearanceAccountBookItems_dataSource"};
	}

	/**
	 * 新增通关电子帐册明细相关信息
	 * @param clearanceAccountBookItems
	 */
	@Override
	public void insert(ClearanceAccountBookItems clearanceAccountBookItems) {
		clearanceAccountBookItemsManager.insertClearanceAccountBookItems(clearanceAccountBookItems);
		}

	/**
	 * 更新通关电子帐册明细相关信息
	 * @param newValues
	 * @param number
	 * @return 通关电子帐册明细相关信息
	 */
	@Override
	public ClearanceAccountBookItems update(Map newValues, String number) {
		return (ClearanceAccountBookItems) clearanceAccountBookItemsManager.updateEntity(newValues, number, ClearanceAccountBookItems.class);
		}

	/**
	 * 删除通关电子帐册明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		clearanceAccountBookItemsManager.deleteEntity(number, ClearanceAccountBookItems.class);
	}

	/**
	 * 查询通关电子帐册明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册明细相关信息
	 */
	@Override
	public List<ClearanceAccountBookItems> doQuery(Map values, int startRow, int endRow) {
		return clearanceAccountBookItemsManager.getClearanceAccountBookItems(values, startRow, endRow);
	}

	/**
	 * 获取所有通关电子帐册明细信息
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册明细信息
	 */
	@Override
	public List<ClearanceAccountBookItems> getList(int startRow, int endRow) {
		return clearanceAccountBookItemsManager.list(0, -1, ClearanceAccountBookItems.class);
	}
}