package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpaceManagerListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CargoSelectPanel extends VLayout{

	private CargoSpaceManagerListgrid originListGrid;
	private CargoSpaceManagerListgrid targetListGrid;
	private List<Record> recordList;
	private HLayout buttonPanel;
	
	public CargoSelectPanel(){
		this(null);
	}
	public CargoSelectPanel(final String repairCodeId){
		recordList = new ArrayList<Record>();
		setWidth100();
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		String cargoModuleName = "stockServiceBusiness.basicData.cargoSpace";
		String cargoDsName = "cargoSpace_dataSource";
		//需补码的货位
		targetListGrid = new CargoSpaceManagerListgrid();
		targetListGrid.setHeight("40%");
		dataSourceTool.onCreateDataSource(cargoModuleName, cargoDsName, new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				targetListGrid.setDataSource(dataSource);
				targetListGrid.drawSimpleListGrid();
				targetListGrid.setShowFilterEditor(false);
				targetListGrid.setSelectionType(SelectionStyle.SIMPLE);
				targetListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
				
				if(repairCodeId != null){
					Criteria criteria = new Criteria("repairCodeId",repairCodeId);
					targetListGrid.fetchData(criteria, new DSCallback() {
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							for(Record record : response.getData()){
								recordList.add(record);
							}
						}
					});
				}
			}
		});
		//所有货位
		originListGrid = new CargoSpaceManagerListgrid();
		originListGrid.setHeight("55%");
		dataSourceTool.onCreateDataSource(cargoModuleName, cargoDsName, new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				originListGrid.setDataSource(dataSource);
				originListGrid.fetchData();
				originListGrid.drawSimpleListGrid();
				originListGrid.setSelectionType(SelectionStyle.SIMPLE);
				originListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
			}
		});
		//货位条件过滤
		originListGrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				originListGrid.fetchData(criteria);
			}
		});
		
		buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(24);
		buttonPanel.setWidth100();
		buttonPanel.setAlign(Alignment.CENTER);

		IButton addToButton = new IButton(ConstantsUtil.buttonConstants.addButton());
		addToButton.setIcon("icons/up_16.png"); 
		addToButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {	
				for(Record record : originListGrid.getSelection()){
					boolean exist = false;
					for(Record r : recordList){
						if( r.getAttribute("id").equals(record.getAttribute("id"))){  
							exist = true;
							break;
						}
					}
					if(!exist){
						recordList.add(record);
					}	
				}
				Record[] records = new Record[recordList.size()];
				recordList.toArray(records);
				targetListGrid.setData(records);
				originListGrid.selectRecords(originListGrid.getSelection(), false);
			}
		});
		IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/remove.png"); 
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(Record record : targetListGrid.getSelection()){
					for(Record r : recordList){
						if(r.getAttribute("id").equals(record.getAttribute("id"))){
							recordList.remove(r);
							break;
						}
					}	
				}
				Record[] records = new Record[recordList.size()];
				recordList.toArray(records);
				targetListGrid.setData(records);
			}
		});
		buttonPanel.addMember(addToButton);
		buttonPanel.addMember(deleteButton);
		
		this.addMember(targetListGrid);
		this.addMember(buttonPanel);
		this.addMember(originListGrid);
	}
	public List<Record> getSelectedRecords(){
		return recordList;
	}
}
