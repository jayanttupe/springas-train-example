package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.GroupNode;
import com.smartgwt.client.widgets.grid.GroupTitleRenderer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CargoSpaceManagerListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CargoSpaceManagerListgrid");
	DataSource userDataSource;
	private DataInfo dataInfo;
	
	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}

	public DataSource getUserDataSource() {
		return userDataSource;
	}

	public CargoSpaceManagerListgrid() {

	}
	
	public void drawSimpleListGrid(){
		setCellHeight(22);
		setCanEdit(false);
		setShowFilterEditor(true);
		setCanGroupBy(true);
		setShowAllRecords(false);
		setGroupByMaxRecords(50000);
		setCanAutoFitFields(false);
		
		// 货位编号 
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		cargoSpaceNumberFiled.setCanFilter(true);
		// 货位尺寸
		ListGridField storageSizeType = new ListGridField("storageSizeType");
		storageSizeType.setCanFilter(true);
		// 承重(KG)
		ListGridField storageWeight = new ListGridField("storageWeight");
		storageWeight.setCanFilter(true);
		
		setFields(cargoSpaceNumberFiled,storageSizeType,storageWeight);
	}
	
	public void drawCargoSpaceManagerListgrid(){
		
		setCellHeight(22);
		setCanEdit(false);
		setShowFilterEditor(true);
		setCanGroupBy(true);
		setShowAllRecords(false);
		setGroupByMaxRecords(50000);
		setCanAutoFitFields(false);

		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		// 库房ID
		ListGridField stockRoomIDFiled = new ListGridField("stockRoomID");
		stockRoomIDFiled.setHidden(true);
		// 货位排
		ListGridField storageRacksRowCodeFiled = new ListGridField("storageRacksRowCode");
		// 库房编号
		ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber");
		// 库房名称
		ListGridField stockRoomNameFiled = new ListGridField("stockRoomChineseName");
		// 所属区域
		ListGridField stockAreaCodeFiled = new ListGridField("stockAreaName", "所属区域");
		// 所属逻辑库
		ListGridField logicStockNameFiled = new ListGridField("logicStockName");
		// 货位尺寸
		ListGridField storageSizeType = new ListGridField("storageSizeType");
		// 承重(KG)
		ListGridField storageWeight = new ListGridField("storageWeight");
		
		cargoSpaceNumberFiled.setCanFilter(true);
		stockRoomNameFiled.setCanFilter(true);
		stockRoomNumberFiled.setCanFilter(true);
		stockAreaCodeFiled.setCanFilter(true);
		logicStockNameFiled.setCanFilter(true);
		storageRacksRowCodeFiled.setCanFilter(true);
		storageSizeType.setCanFilter(true);
		storageWeight.setCanFilter(true);
		setFields(cargoSpaceNumberFiled
				,stockRoomIDFiled
				,stockRoomNumberFiled
				,stockRoomNameFiled
				,stockAreaCodeFiled
				,storageRacksRowCodeFiled
				,logicStockNameFiled
				,storageSizeType
				,storageWeight
				);
	    
	    storageRacksRowCodeFiled.setGroupTitleRenderer(new GroupTitleRenderer() {  
            public String getGroupTitle(Object groupValue, GroupNode groupNode, ListGridField field, String fieldName, ListGrid grid) {  
                String baseTitle =field.getTitle()+" 的货位个数为：";  
                baseTitle += " (" + groupNode.getGroupMembers().length + "个)";  
                return baseTitle;  
            }  
        });   
	}
}
