package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListManager;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：装箱单相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PackingListDatasourceAction implements DataSourceAction<PackingList>{
	@Autowired
	private PackingListManager packingListManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"packingList_dataSource"};
	}

	/**
	 * 新增装箱单相关信息
	 * @param packingList
	 */
	@Override
	public void insert(PackingList packingList) {
		List<PackingListPartItems> newPackingListPartItemsList =
			OneToManyTools.ConvertToEntity(packingList.getPackingListPartItemsList(), PackingListPartItems.class);
		packingList.setPackingListPartItemsList(newPackingListPartItemsList);

		packingListManager.savePackingList(packingList);
	}

	/**
	 * 更新装箱单相关信息
	 * @param newValues
	 * @param number
	 * @return 装箱单相关信息
	 */
	@Override
	public PackingList update(Map newValues, String number) {
		if (newValues.containsKey("operatorType")
				&& newValues.get("operatorType") != null)
		{
			if (newValues.get("operatorType").equals("packing_release"))
			{
				// 设置状态为合格放行
				newValues.put("status", String.valueOf(OutStockStatus.CheckOut));
				return packingListManager.updateStatus(newValues);
			} else {
				return null;
			}
		} else {
			PackingList packingList = new PackingList();		
			BeanPropUtil.fillEntityWithMap(packingList, newValues);
			
			List<PackingListPartItems> newPackingListPartItemsList =
				OneToManyTools.ConvertToEntity(packingList.getPackingListPartItemsList(), PackingListPartItems.class);
			packingList.setPackingListPartItemsList(newPackingListPartItemsList);

			return packingListManager.savePackingList(packingList);
		}
	
	}

	/**
	 * 删除装箱单相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		packingListManager.deleteEntity(number, PackingList.class);
	}

	/**
	 * 查询装箱单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 装箱单相关信息
	 */
	@Override
	public List<PackingList> doQuery(Map values, int startRow, int endRow) {
		return packingListManager.getPackingList(values, 0, -1);
	}

	/**
	 * 获取所有装箱单信息 
	 * @param startRow
	 * @param endRow
	 * @return 装箱单信息
	 */
	@Override
	public List<PackingList> getList(int startRow, int endRow) {
		return packingListManager.getPackingList(null, 0, -1);
	}
}