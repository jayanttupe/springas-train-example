package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetHistory;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

/**
 * 描述：收料单历史记录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ReceivingSheetHistoryDatasourceAction implements
		DataSourceAction<ReceivingSheet> {

	@Autowired
	private ReceivingSheetHistory receivingSheetHistory;

	@Override
	public String[] getBindDsName() {
		return new String[] { "receivingSheetHistory_dataSource" };
	}

	/**
	 * 新增收料单历史记录相关信息
	 * @param item
	 */
	@Override
	public void insert(ReceivingSheet item) {
	}

	/**
	 * 更新收料单历史记录相关信息
	 * @param newValues
	 * @param itemID
	 * @return 收料单历史记录相关信息
	 */
	@Override
	public ReceivingSheet update(Map<String, Object> newValues, String itemID) {
		return null;
//		return (ReceivingSheet) receivingSheetHistory.updateEntity(newValues,
//				itemID, ReceivingSheet.class);
	}
	
	/**
	 * 删除收料单历史记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询收料单历史记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 收料单历史记录相关信息
	 */
	@Override
	public List<ReceivingSheet> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return receivingSheetHistory.getReceivingSheetHistory(values, startRow, endRow);
	}

	/**
	 * 获取所有收料单历史记录信息
	 * @param startRow
	 * @param endRow
	 * @return 收料单历史记录信息
	 */
	@Override
	public List<ReceivingSheet> getList(int startRow, int endRow) {
		return receivingSheetHistory.getReceivingSheetHistory(null, 0, -1);
	}
}
