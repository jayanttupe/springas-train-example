package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * 移库记录明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockMovingRecordItemsManager extends CommonManager<StockMovingRecordItems>{

	/**
	 * 根据id进行查找移库记录明细信息
	 * @param id
	 * @return 移库记录明细信息
	 */
	public List<StockMovingRecordItems> findby(String id);
	
	/**
	 * 查找所有移库记录明细信息
	 * @return 移库记录明细信息
	 */
	public List<StockMovingRecordItems> findbyall();

	/**
	 * 新增移库记录明细信息
	 * @param stockMovingRecordItems
	 */
    public void InsertStockMovingRecordItems(StockMovingRecordItems stockMovingRecordItems);
	
    /**
     * 获取相关移库记录明细信息
     * @param map
     * @param startRow
     * @param endRow
     * @return 移库记录明细信息
     */
	public List<StockMovingRecordItems> getStockMovingRecordItems(Map map, int startRow, int endRow);
}