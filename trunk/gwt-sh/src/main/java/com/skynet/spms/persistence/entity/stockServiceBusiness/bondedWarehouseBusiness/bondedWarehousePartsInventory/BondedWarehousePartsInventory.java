package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehousePartsInventory;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:12
 */
public class BondedWarehousePartsInventory extends PartsInventoryRecord {

	/**
	 * 通关电子帐册项次号
	 */
	private String accountBookItemsNumber;
	/**
	 * 通关电子帐册号
	 */
	private String clearanceAccountBookNumber;
	/**
	 * 报关商品HS编码
	 */
	private String hsCode;

	public BondedWarehousePartsInventory(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}