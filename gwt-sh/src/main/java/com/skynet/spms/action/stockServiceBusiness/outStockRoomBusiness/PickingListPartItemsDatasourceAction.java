package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * 描述：配料单相关明细信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickingListPartItemsDatasourceAction implements DataSourceAction<PickingListPartItems>{
	@Autowired
	private PickingListPartItemsManager pickingListPartItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"pickingListPartItems_dataSource"};
	}

	/**
	 * 新增配料单相关明细信息
	 * @param pickingList
	 */
	@Override
	public void insert(PickingListPartItems pickingList) {
		pickingListPartItemsManager.insertEntity(pickingList);
	}

	/**
	 * 更新配料单相关明细信息
	 * @param newValues
	 * @param number
	 * @return 配料单相关明细信息
	 */
	@Override
	public PickingListPartItems update(Map newValues, String number) {
		return (PickingListPartItems) pickingListPartItemsManager.updateEntity(newValues, number, PickingListPartItems.class);
	}

	/**
	 * 删除配料单相关明细信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		pickingListPartItemsManager.deleteEntity(number, PickingListPartItems.class);
	}

	/**
	 * 查询配料单相关明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单相关明细信息
	 */
	@Override
	public List<PickingListPartItems> doQuery(Map values, int startRow, int endRow) {
		return pickingListPartItemsManager.getPickingListPartItems(values, 0, -1);
	}

	/**
	 * 获取所有配料单明细信息
	 * @param startRow
	 * @param endRow
	 * @return 配料单明细信息
	 */
	@Override
	public List<PickingListPartItems> getList(int startRow, int endRow) {
		return pickingListPartItemsManager.getPickingListPartItems(null, 0, -1);
	}
}