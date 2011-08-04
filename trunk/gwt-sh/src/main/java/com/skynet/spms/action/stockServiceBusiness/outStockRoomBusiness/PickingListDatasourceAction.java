package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

/**
 * 描述：配料单相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickingListDatasourceAction implements DataSourceAction<PickingList>{
	@Autowired
	private PickingListManager pickingListManager;
	@Autowired
	private StockTaskManager stockTaskManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"pickingList_dataSource"};
	}

	/**
	 * 新增配料单相关信息
	 * @param pickingList
	 */
	@Override
	public void insert(PickingList pickingList) {
		// 设置状态为(Picking:已配料)
		pickingList.setStatus(OutStockStatus.Picking);
		pickingListManager.SavePickingList(pickingList);
	}

	/**
	 * 更新配料单相关信息
	 * @param newValues
	 * @param number
	 * @return 配料单相关信息
	 */
	@Override
	public PickingList update(Map newValues, String number) {
		if (newValues.containsKey("operatorType")
				&& newValues.get("operatorType") != null)
		{
			ArrayList pickinglistIDList = (ArrayList)newValues.get("pickingListID");
			String[] pickingListIDs = new String[pickinglistIDList.size()];
			pickinglistIDList.toArray(pickingListIDs);
			
			if (newValues.get("operatorType").equals("picking_instruct"))
			{
				// 生成拣货计划并将状态改为"2:已下达"
				stockTaskManager.insertPickingTask(pickingListIDs);
			}

			return null;
		} else {

			PickingList pickingList = new PickingList();
			BeanPropUtil.fillEntityWithMap(pickingList, newValues);

			return pickingListManager.SavePickingList(pickingList);
		}
	}

	/**
	 * 删除配料单相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		pickingListManager.deletePickingList(number);
	}

	/**
	 * 查询配料单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单相关信息
	 */
	@Override
	public List<PickingList> doQuery(Map values, int startRow, int endRow) {
		values.put("status", String.valueOf(OutStockStatus.Picking));
		return pickingListManager.getPickingList(values, 0, -1);
	}

	/**
	 * 获取所有配料单信息
	 * @param startRow
	 * @param endRow
	 * @return 配料单信息
	 */
	@Override
	public List<PickingList> getList(int startRow, int endRow) {
		// 取得所有已配料的数据
		Map map = new HashMap();
		map.put("status", String.valueOf(OutStockStatus.Picking));
		return pickingListManager.getPickingList(map, 0, -1);
	}
}