package com.skynet.spms.client.gui.partcatalog.technicalCatalog.AircraftConfiguration;


import com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog.AircraftConfigCatalogListGrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.VLayout;
/*
 * 飞机构型管理面板
 */
public class AircraftConfigurationPanel extends VLayout {
	private AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid;

	//飞机构型面板构造函数
	public AircraftConfigurationPanel() {
		//实例化飞机构型表格控件
		aircraftConfigCatalogListGrid = new AircraftConfigCatalogListGrid(false);

		this.addMember(aircraftConfigCatalogListGrid);
	}
	
	public void fetchData(String partIndexId){
		Criteria criteria = new Criteria("partIndexId",partIndexId);
		aircraftConfigCatalogListGrid.fetchData(criteria);
		
	}
	public void clearData(){
		aircraftConfigCatalogListGrid.setData(new Record[0]);
	}
}