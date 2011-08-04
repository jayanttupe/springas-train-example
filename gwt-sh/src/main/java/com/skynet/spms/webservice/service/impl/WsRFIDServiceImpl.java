package com.skynet.spms.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListBoxItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.PartsInventoryRecordManager;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.CargoSpaceManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.webservice.WebServiceUtils;
import com.skynet.spms.webservice.entity.Check4DoorControlInputParameters;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.GetRFIDSerialInputParameters;
import com.skynet.spms.webservice.entity.GetRFIDSerialOutputParameters;
import com.skynet.spms.webservice.entity.LocationInfo;
import com.skynet.spms.webservice.entity.LocationPartItem;
import com.skynet.spms.webservice.entity.QueryLocationOutParameters;
import com.skynet.spms.webservice.entity.QueryLocationParameters;
import com.skynet.spms.webservice.entity.RFIDTagUUID;
import com.skynet.spms.webservice.entity.VisualLocationInputParameters;
import com.skynet.spms.webservice.entity.VisualLocationOutputParameters;

@Service
public class WsRFIDServiceImpl implements
		com.skynet.spms.webservice.service.WsRFIDService {

	@Autowired
	private CargoSpaceManager cargoSpaceManager;
	@Autowired
	private PartsInventoryRecordManager partsInventoryRecordManager;
	@Autowired
	private PackingListBoxItemsManager packingListBoxItemsManager;
	
	@Override
	public VisualLocationOutputParameters getVirtualLocaltion(
			VisualLocationInputParameters request) throws FaultResponse {
		VisualLocationOutputParameters result = new VisualLocationOutputParameters();
		
		// 货位编号
		String locationNumber = request.getLocationNumber();
		Map map = new HashMap();
		map.put("cargoSpaceNumber", locationNumber);
		List<PartsInventoryRecord> partsInventoryRecordList = partsInventoryRecordManager.getPartsInventoryRecord(map, 0, -1);
		
		for (int i = 0; i < partsInventoryRecordList.size(); i++) {
			PartsInventoryRecord partsInventoryRecord = (PartsInventoryRecord)partsInventoryRecordList.get(i);
			LocationPartItem locationPartItem = new LocationPartItem();
			
			// 件号
			locationPartItem.setPartNumber(partsInventoryRecord.getPartNumber());
			// 件名称
			locationPartItem.setPartName(partsInventoryRecord.getPartName());
			// 序号/批号
			locationPartItem.setPartSerialNumber(partsInventoryRecord.getPartSerialNumber());
			// 货位编号
			locationPartItem.setLotNumber(partsInventoryRecord.getCargoSpaceNumber());
			// 当前库存数量
			locationPartItem.setQuantity(Double.valueOf(partsInventoryRecord.getBalanceQuantity()));
			// 单位
			if (partsInventoryRecord.getUnit() != null) {
			    locationPartItem.setUnitOfMeasureCode(partsInventoryRecord.getUnit().toString());	
			}
			// 条码标签唯一编号
		    locationPartItem.setBarcodeTagUUID(partsInventoryRecord.getBarcodeTagUUID());
		    // RFID标签唯一编号
		    locationPartItem.setRFIDTagUUID(partsInventoryRecord.getrFIDTagUUID());
		    // 出厂日期 
		    if (partsInventoryRecord.getBuildDate() != null) {
			    locationPartItem.setManufacturDate(WebServiceUtils.convertDate(partsInventoryRecord.getBuildDate()));	
		    }
		    // 制造商代码
		    locationPartItem.setManufacturerCode(partsInventoryRecord.getManufacturerCode());
		    // 库房编号
		    locationPartItem.setStockRoomNumber(partsInventoryRecord.getStockRoomNumber());
		    // 备件状态
		    locationPartItem.setPartStatusCode(partsInventoryRecord.getPartStatus());

			result.getLocationPartItem().add(locationPartItem);
		}
		return result;
	}

	@Override
	public QueryLocationOutParameters getLocationInfo(
			QueryLocationParameters request) throws FaultResponse {
		
		QueryLocationOutParameters output = new QueryLocationOutParameters();
		List<LocationInfo> locList = new ArrayList<LocationInfo>();
		
		// 取得所有的货位
		List<CargoSpace> cargoSpaceList = cargoSpaceManager.getAllCargoSpace(null, 0, 100);
		for (CargoSpace cargoSpace : cargoSpaceList) {
			LocationInfo locationInfo = new LocationInfo();
			locationInfo.setBarcodeTagUUID("");
			locationInfo.setRFIDTagUUID("");
			locationInfo.setFullLocationNumber(cargoSpace.getCargoSpaceNumber());
			locList.add(locationInfo);
		}
		
		output.setLocationList(locList);
		return output;
	}

	/**
	 * 门禁报警处理
	 */
	@Override
	public boolean check4DoorControl(Check4DoorControlInputParameters request)
			throws FaultResponse {
		List<RFIDTagUUID> fFIDTagUUIDList = request.getRFIDTagUUID();
		String[] rFIDTagUUIDs = new String[fFIDTagUUIDList.size()];
		for (int i = 0; i < fFIDTagUUIDList.size(); i++) {
			rFIDTagUUIDs[i] = ((RFIDTagUUID)fFIDTagUUIDList.get(i)).getRFIDTagUUID();
		}
		return packingListBoxItemsManager.isExistPackingBox(rFIDTagUUIDs);
	}

	@Override
	public GetRFIDSerialOutputParameters getRFIDSerial(
			GetRFIDSerialInputParameters request) throws FaultResponse {
		// TODO Auto-generated method stub
		return null;
	}

}
