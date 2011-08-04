package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

@ViewFormAnno(value="pickingListPartItems.id",entityCls=PickingListPartItems.class)
public class PickingInventoryForm extends BaseIDEntity{

	private PartsInventoryRecord partsInventoryRecord;
	
	private PickingListPartItems pickingListPartItems;

	public PartsInventoryRecord getPartsInventoryRecord() {
		return partsInventoryRecord;
	}

	public void setPartsInventoryRecord(PartsInventoryRecord partsInventoryRecord) {
		this.partsInventoryRecord = partsInventoryRecord;
	}

	public PickingListPartItems getPickingListPartItems() {
		return pickingListPartItems;
	}

	public void setPickingListPartItems(PickingListPartItems pickingListPartItems) {
		this.pickingListPartItems = pickingListPartItems;
	}
}
