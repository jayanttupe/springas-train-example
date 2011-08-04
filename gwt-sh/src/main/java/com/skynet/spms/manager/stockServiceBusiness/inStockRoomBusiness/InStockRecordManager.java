package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecordForPrint;

/**
 * 入库记录管理Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface InStockRecordManager extends CommonManager<InStockRecord> {

	/**
	 * 更新入库记录
	 * @param inStockRecord
	 * @return 入库记录
	 */
	public InStockRecord updateRecord(InStockRecord inStockRecord);

	/**
	 * 获取入库记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 入库记录
	 */
	public List<InStockRecord> getInStockRecord(Map values, int startRow, int endRow);

	/**
	 * 获取需要打印的入库记录
	 * @param inStockRecords
	 * @return 需要打印的入库记录
	 */
	List<InStockRecordForPrint> getInStockRecordForPrint(String[] inStockRecords);

}