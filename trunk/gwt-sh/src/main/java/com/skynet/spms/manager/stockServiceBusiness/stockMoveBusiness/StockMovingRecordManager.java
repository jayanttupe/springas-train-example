package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecord;

/**
 * 移库记录信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockMovingRecordManager extends CommonManager<StockMovingRecord>{

	/**
	 * 更新移库记录信息
	 * @param map
	 * @return 移库记录信息
	 */
	public StockMovingRecord updateRecord(Map map);
	
	/**
	 * 查询移库记录信息
	 * @param state
	 * @param state2
	 * @return 移库记录信息
	 */
	public List<StockMovingRecord> findbystate(String state,String state2);
	
	/**
	 * 更新记录状态
	 * @param map
	 * @return 移库记录信息
	 */
	public StockMovingRecord updateRecordstate(Map map);
	
	/**
	 * 查询所有移库记录
	 * @return 所有移库记录
	 */
	public List<StockMovingRecord> findbyall();
	
	/**
	 * 获取相关移库信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 相关移库信息
	 */
	public List<StockMovingRecord> getAllByCondition(Map values, int startRow, int endRow);
	
	/**
	 * 保存移库记录信息
	 * @param stockMovingRecord
	 * @return 移库记录信息
	 */
	public StockMovingRecord SaveStockMovingRecord(StockMovingRecord stockMovingRecord);
	
	/**
	 * 获取所有通过的记录
	 * @return
	 */
	public List<StockMovingRecord> GetAllbyApply();
	
	/**
	 * 获取所有审批的记录
	 * @return
	 */
	public List<StockMovingRecord> GetAllbyApproval();
	
}