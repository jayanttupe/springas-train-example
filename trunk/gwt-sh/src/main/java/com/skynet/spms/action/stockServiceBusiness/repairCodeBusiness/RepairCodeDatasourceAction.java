package com.skynet.spms.action.stockServiceBusiness.repairCodeBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodeManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCode;

/**
 * 描述：航材补码相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class RepairCodeDatasourceAction implements DataSourceAction<RepairCode>{
	@Autowired
	private RepairCodeManager repairCodeManager;
	@Autowired
	private StockTaskManager stockTaskManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"repairCode_dataSource"};
	}

	/**
	 * 新增航材补码相关信息
	 * @param item
	 */
	@Override
	public void insert(RepairCode item) {
		repairCodeManager.saveRepairCode(item);
	}
	
	/**
	 * 更新航材补码相关信息
	 * @param newValues
	 * @param itemID
	 * @return 航材补码相关信息
	 */
	@Override
	public RepairCode update(Map newValues, String itemID) {
		List<String> itemIdList = (List<String>)newValues.remove("itemIds");
		if(itemID.equals("repairCodeTask") && itemIdList != null){
			String[] itemIds = new String[itemIdList.size()];
			itemIdList.toArray(itemIds);
			stockTaskManager.insertRepairCodeTask(itemIds);
			return null;
		}else{
			RepairCode repairCode = repairCodeManager.updateRepairCode(newValues, itemID);
			return repairCode;
		}
	}

	/**
	 * 删除航材补码相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		repairCodeManager.deleteRepairCode(itemID);
	}
	
	/**
	 * 查询航材补码相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材补码相关信息
	 */
	@Override
	public List<RepairCode> doQuery(Map values,
			int startRow, int endRow) {
		return repairCodeManager.getRepairCode(values, 0, -1);
	}

	/**
	 * 获取所有航材补码信息
	 * @param startRow
	 * @param endRow
	 * @return 航材补码信息
	 */
	@Override
	public List<RepairCode> getList(int startRow, int endRow) {
		
		return repairCodeManager.getRepairCode(null, 0, -1);
	}

}
