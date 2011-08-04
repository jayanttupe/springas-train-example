package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListBoxItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListPartItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListBoxItems;

/**
 * 描述：装箱单明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PackingListBoxItemsDatasourceAction implements DataSourceAction<PackingListBoxItems>{
	@Autowired
	private PackingListBoxItemsManager packingListBoxItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"packingListBoxItems_dataSource"};
	}

	/**
	 * 新增装箱单明细相关信息
	 * @param packingListBoxItems
	 */
	@Override
	public void insert(PackingListBoxItems packingListBoxItems) {
		packingListBoxItemsManager.insertEntity(packingListBoxItems);
	}

	/**
	 * 更新装箱单明细相关信息
	 * @param newValues
	 * @param number
	 * @return 装箱单明细相关信息
	 */
	@Override
	public PackingListBoxItems update(Map newValues, String number) {
		return (PackingListBoxItems) packingListBoxItemsManager.updateEntity(newValues, number, PackingListBoxItems.class);
	}

	/**
	 * 删除装箱单明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		packingListBoxItemsManager.deleteEntity(number, PackingListBoxItems.class);
	}

	/**
	 * 查询装箱单明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 装箱单明细相关信息
	 */
	@Override
	public List<PackingListBoxItems> doQuery(Map values, int startRow, int endRow) {
		return packingListBoxItemsManager.getPackingListBoxItems(values, 0, -0);
	}

	/**
	 * 获取所有装箱单明细信息
	 * @param startRow
	 * @param endRow 
	 * @return 装箱单明细信息
	 */
	@Override
	public List<PackingListBoxItems> getList(int startRow, int endRow) {
		return packingListBoxItemsManager.list(0, -1, PackingListBoxItems.class);
	}
}