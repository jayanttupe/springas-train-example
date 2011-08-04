package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

/*import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RecordSelectPanel extends VLayout{

	private ListGrid originListGrid;
	private ListGrid targetListGrid;
	private List<Record> recordList;
	private HLayout buttonPanel;
	private VLayout originPanel;
	private VLayout targetPanel;
	
	public RecordSelectPanel(){
		recordList = new ArrayList<Record>();
		setWidth100();
		setHeight100();
		
		targetPanel = new VLayout();
		targetPanel.setWidth100();
		targetPanel.setHeight("45%");
		
		originPanel = new VLayout();
		originPanel.setWidth100();
		originPanel.setHeight100();
		
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
		
		this.addMember(targetPanel);
		this.addMember(buttonPanel);
		this.addMember(originPanel);
	}
	
	public void setOriginListGrid(ListGrid originList){
		if(originListGrid != null){
			originListGrid.setVisible(false);
			originPanel.removeChild(originListGrid);
		}
		originListGrid = originList;
		originPanel.addChild(originListGrid);
		originListGrid.setVisible(true);
	}
	
	public void setTargetListGrid(ListGrid targetList){
		if(targetListGrid != null){
			targetListGrid.setVisible(false);
			targetPanel.removeChild(targetListGrid);
		}
		targetListGrid = targetList;
		targetListGrid.setVisible(true);
		originPanel.addChild(targetListGrid);
		
		recordList.clear();
		for(Record record : targetListGrid.getRecords()){
			recordList.add(record);
		}
	}
	
	public boolean isInit(){
		return targetListGrid==null && originListGrid==null;
	}
}
*/