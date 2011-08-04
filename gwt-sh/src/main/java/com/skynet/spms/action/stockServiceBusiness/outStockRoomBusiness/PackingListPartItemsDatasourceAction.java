package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListPartItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * 描述：装箱单备件明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PackingListPartItemsDatasourceAction implements DataSourceAction<PackingListPartItems>{
	@Autowired
	private PackingListPartItemsManager packingListPartItemsManager;
	@Autowired
	private PickingListPartItemsManager pickingListPartItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"packingListPartItems_dataSource"};
	}

	/**
	 * 新增装箱单备件明细相关信息
	 * @param packingListPartItems
	 */
	@Override
	public void insert(PackingListPartItems packingListPartItems) {
		packingListPartItemsManager.insertEntity(packingListPartItems);
	}

	/**
	 * 更新装箱单备件明细相关信息
	 * @param newValues
	 * @param number
	 * @return 装箱单备件明细相关信息
	 */
	@Override
	public PackingListPartItems update(Map newValues, String number) {
		packingListPartItemsManager.savePackingListPartItems(newValues);
		return null;
	}

	/**
	 * 删除装箱单备件明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		packingListPartItemsManager.deleteEntity(number, PackingListPartItems.class);
	}

	/**
	 * 查询装箱单备件明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 装箱单备件明细相关信息
	 */
	@Override
	public List<PackingListPartItems> doQuery(Map values, int startRow, int endRow) {
		// 根据装箱单ID取得备件明细数据
		if (values.containsKey("packingListID"))
		{
			return packingListPartItemsManager.getPackingListPartItems(values, 0, -1);	
		}
		// 根据配料单ID取得备件明细数据
		if (values.containsKey("pickingListID"))
		{
			List<PackingListPartItems> packingListPartItemsList = new ArrayList<PackingListPartItems>();
			List<PickingListPartItems> pickingListPartItemsList = pickingListPartItemsManager.getPickingListPartItems(values, 0, -1);
			
			for (int i = 0; i < pickingListPartItemsList.size(); i++)
			{
				PickingListPartItems pickingListPartItems = (PickingListPartItems)pickingListPartItemsList.get(i);
				PackingListPartItems packingListPartItems = new PackingListPartItems();
				// 件号
				packingListPartItems.setPartNumber(pickingListPartItems.getPartNumber());
				// 序号/批号
				packingListPartItems.setPartSerialNumber(pickingListPartItems.getPartSerialNumber());
				// 件名称
				packingListPartItems.setPartName(pickingListPartItems.getPartName());
				// 单位
				packingListPartItems.setUnit(pickingListPartItems.getUnit());
				// 制造商
				packingListPartItems.setManufacturer(pickingListPartItems.getManufacturer());
				// 实发数量
				packingListPartItems.setSendQty(pickingListPartItems.getSendQty());
				// 收料追溯号
				packingListPartItems.setReceivingSheetID("");
				// 随件证件
				packingListPartItems.setCertificateType("");
				// 分箱号
				packingListPartItems.setBoxID("");
				// 状态
				packingListPartItems.setStatus("");
				// 箱号
				packingListPartItems.setBoxID("");
				packingListPartItemsList.add(packingListPartItems);
			}
			
			return packingListPartItemsList;
		}
		else
		{
			return null;	
		}
	}

	/**
	 * 获取所有装箱单备件明细信息
	 * @param startRow
	 * @param endRow
	 * @return 装箱单备件明细信息
	 */
	@Override
	public List<PackingListPartItems> getList(int startRow, int endRow) {
		return packingListPartItemsManager.list(0, -1, PackingListPartItems.class);
	}
}