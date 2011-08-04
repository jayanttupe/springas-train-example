package com.skynet.spms.action.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultTrackManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

/**
 * 描述：盘点跟踪业务相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckResultTrackDatasourceAction implements DataSourceAction<StockCheck>{

	@Autowired
	private StockCheckResultTrackManager stockCheckResultTrackManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"stockCheckResultTrack_dataSource"};
	}

	/**
	 * 新增盘点跟踪业务相关信息
	 * @param item
	 */
	@Override
	public void insert(StockCheck item) {
	}

	/**
	 * 更新盘点跟踪业务相关信息
	 * @param newValues
	 * @param itemID
	 * @return 盘点跟踪业务相关信息
	 */
	@Override
	public StockCheck update(Map<String, Object> newValues, String itemID) {
		return null;
	}

	/**
	 * 删除盘点跟踪业务相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
	}

	/**
	 * 查询盘点跟踪业务相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点跟踪业务相关信息
	 */
	@Override
	public List<StockCheck> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return stockCheckResultTrackManager.getStockCheckResultTrackByCondition(values, 0, -1);
	}

	/**
	 * 获取所有盘点跟踪业务信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点跟踪业务信息
	 */
	@Override
	public List<StockCheck> getList(int startRow, int endRow) {
		return stockCheckResultTrackManager.getStockCheckResultTrack(0, -1);
	}

}
