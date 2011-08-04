package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.godownEntry;
import java.util.Date;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;

/**
 * 入库单为动态生成，根据过滤条件生成入库单，如可以按入库日期进行查询，从而打印出当天的入库单，或根据入库人员进行打印。
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:13
 */
public class GodownEntry extends BaseEntity {

	/**
	 * 入库单日期
	 */
	private Date godownEntryDate;
	/**
	 * 入库单编号
	 */
	private String godownEntryNumber;
	/**
	 * 入库记录列表
	 */
	private String inStockRecordNumber;

	public GodownEntry(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}