package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListManager;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

/**
 * 描述：配料单相关记录处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickingRecordDatasourceAction implements DataSourceAction<PickingList>{
	@Autowired
	private PickingListManager pickingListManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"pickingRecord_dataSource"};
	}

	/**
	 * 新增配料单相关记录
	 * @param pickingList
	 */
	@Override
	public void insert(PickingList pickingList) {
	}

	/**
	 * 更新配料单相关记录
	 * @param newValues
	 * @param number
	 * @return 配料单相关记录
	 */
	@Override
	public PickingList update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除配料单相关记录
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询配料单相关记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单相关记录
	 */
	@Override
	public List<PickingList> doQuery(Map values, int startRow, int endRow) {
		// 根据条件取得所有已拣货的数据
		values.put("status", String.valueOf(OutStockStatus.Pick));
		return pickingListManager.getPickingList(values, 0, -1);
	}

	/**
	 * 获取所有配料单记录
	 * @param startRow
	 * @param endRow
	 * @return 配料单记录
	 */
	@Override
	public List<PickingList> getList(int startRow, int endRow) {
		// 取得所有已拣货的数据
		Map map = new HashMap();
		map.put("status", String.valueOf(OutStockStatus.Pick));
		return pickingListManager.getPickingList(map, 0, -1);
	}
}