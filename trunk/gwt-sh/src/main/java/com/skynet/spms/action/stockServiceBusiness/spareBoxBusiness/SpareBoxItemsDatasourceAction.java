package com.skynet.spms.action.stockServiceBusiness.spareBoxBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.reparePartBusiness.ReparePartBusinessManager;
import com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.SpareBoxItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness.ReparePartBusiness;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBoxItems;

/**
 * 描述：航材包相关明细信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class SpareBoxItemsDatasourceAction implements DataSourceAction<SpareBoxItems>{
	@Autowired
	private SpareBoxItemsManager spareBoxItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"spareBoxItems_dataSource"};
	}

	/**
	 * 新增航材包相关明细信息
	 * @param spareBoxItems
	 */
	@Override
	public void insert(SpareBoxItems spareBoxItems) {
		spareBoxItemsManager.insertEntity(spareBoxItems);
	}

	/**
	 * 更新航材包相关明细信息
	 * @param newValues
	 * @param number
	 * @return 航材包相关明细信息
	 */
	@Override
	public SpareBoxItems update(Map newValues, String number) {
		return (SpareBoxItems) spareBoxItemsManager.updateEntity(newValues, number, SpareBoxItems.class);
	}

	/**
	 * 删除航材包相关明细信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		spareBoxItemsManager.deleteEntity(number, SpareBoxItems.class);
	}

	/**
	 * 查询航材包相关明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材包相关明细信息
	 */
	@Override
	public List<SpareBoxItems> doQuery(Map values, int startRow, int endRow) {
		return spareBoxItemsManager.getSpareBoxItems(values, 0, -1);
	}

	/**
	 * 获取所有航材包明细信息
	 * @param startRow
	 * @param endRow
	 * @return 航材包明细信息
	 */
	@Override
	public List<SpareBoxItems> getList(int startRow, int endRow) {
		return spareBoxItemsManager.getSpareBoxItems(null, 0, -1);
	}
}