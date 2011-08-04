/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-18   山云峰	   
 * Date        Author      Changes
 * 2011-3-24   黄贇 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

/**
 * Description 用户添加至用户组窗体
 * @see  com.skynet.spms.client.gui.basedatamanager.organization.usergroup.UserAddToGroupWindow
 * @author 山云峰
 * @version 0.5
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;


public class RepairCodeEditWindow extends BaseWindow {

	private DynamicForm formBase;
	private PartSelectPanel partSelectPanel;
	private CargoSelectPanel cargoSelectPanel;
	private HLayout buttonPanel;
	private TabSet tabSet;
	private Tab partSelectTab;
	private Tab cargoSelectTab;
	public RepairCodeEditWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, RepairCodeEditWindow.this, -1);
			}
		});
		
		final RepairCodeListgrid repairCodeListgrid = (RepairCodeListgrid)listGrid;
		final DataInfo dataInfo = repairCodeListgrid.getDataInfo();
		String repairCodeId = null;
		Record selectedRecord = null;
		String currentRepairCodeType = null;
		if(!repairCodeListgrid.isPassByNew()){
			selectedRecord = repairCodeListgrid.getSelectedRecord();
			repairCodeId = selectedRecord.getAttribute("id");
			currentRepairCodeType = selectedRecord.getAttribute("repairCodeType");
		}
		 
		tabSet = new TabSet();
	    //航材选项卡
	    partSelectTab = new Tab(ConstantsUtil.stockConstants.choosePart());
	    partSelectPanel = new PartSelectPanel(repairCodeId);
	    partSelectTab.setPane(partSelectPanel);
	    
	    //货位选项卡
	    cargoSelectTab = new Tab(ConstantsUtil.stockConstants.chooseCargoSpace());
	    cargoSelectPanel = new CargoSelectPanel(repairCodeId);
	    cargoSelectTab.setPane(cargoSelectPanel);
	    
	    if(RepairCodeType.CARGE.equals(currentRepairCodeType)){
	    	tabSet.addTab(cargoSelectTab);
		    tabSet.addTab(partSelectTab);
		    partSelectTab.setDisabled(true);
	    }else {
	    	tabSet.addTab(partSelectTab);
		    tabSet.addTab(cargoSelectTab);
		    cargoSelectTab.setDisabled(true);
	    }
		
		formBase = new DynamicForm();
		formBase.setDataSource(repairCodeListgrid.getDataSource()); 
		formBase.setWidth(590);
		formBase.setPadding(5);
		formBase.setLayoutAlign(Alignment.LEFT);
        formBase.setAutoFocus(false);  
        formBase.setUseAllDataSourceFields(false); 
        formBase.setNumCols(4);  
        formBase.setColWidths(100,140,100,250);
		
        List<FormItem> itemList = new ArrayList<FormItem>();
         
        //补码任务ID
        final HiddenItem hdnId = new HiddenItem("id");
        itemList.add(hdnId);
        
        // 任务编号
		final TextItem taskNumber = new TextItem("taskNumber");
		taskNumber.setWidth(140);
		taskNumber.setDefaultValue("业务编号系统自动生成");
		taskNumber.setDisabled(true);
		itemList.add(taskNumber);
		
		// 补码原因
		final TextItem repairCodeReason = new TextItem("repairCodeReason");
		repairCodeReason.setWidth(250);
		itemList.add(repairCodeReason);
		
		// 补码类型
		final SelectItem repairCodeType = (SelectItem)dataInfo.getFieldInfoByName("repairCodeType").generFormField();
		repairCodeType.setWidth(140);
		repairCodeType.setDefaultToFirstOption(true);
		itemList.add(repairCodeType);
		repairCodeType.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if(event.getValue().equals(RepairCodeType.SPARE_CODE)){
					if(partSelectTab != null){
						partSelectTab.setDisabled(false);
						tabSet.selectTab(partSelectTab);
						cargoSelectTab.setDisabled(true);
					}
					
				}else if(event.getValue().equals(RepairCodeType.CARGE)){
					if(cargoSelectTab != null){
						cargoSelectTab.setDisabled(false);
						tabSet.selectTab(cargoSelectTab);
						partSelectTab.setDisabled(true);
					}
				}	
			}
		});
		
		// 备注
		final TextAreaItem remark = new TextAreaItem("remark");
		remark.setWidth(250);
		remark.setHeight(60);
		remark.setRowSpan(2);
		itemList.add(remark);
		
		// 补码种类
		final SelectItem repairType = (SelectItem)dataInfo.getFieldInfoByName("repairType").generFormField();
		repairType.setWidth(140);
		repairType.setDefaultToFirstOption(true);
		itemList.add(repairType);
		
		//执行修改操作时设置表单元素的初始值
		if(!repairCodeListgrid.isPassByNew()){
			hdnId.setValue(repairCodeId);
			taskNumber.setValue(selectedRecord.getAttribute("taskNumber"));
			repairCodeReason.setValue(selectedRecord.getAttribute("repairCodeReason"));
			remark.setValue(selectedRecord.getAttribute("remark"));
			repairType.setValue(selectedRecord.getAttribute("repairType"));
			repairCodeType.setValue(selectedRecord.getAttribute("repairCodeType"));
		}
		
		FormItem[] items = new FormItem[itemList.size()];
	    itemList.toArray(items);
	    formBase.setFields(items);
	    
	    //保存按钮条
		buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(24);
        
	    IButton saveButton = new IButton();
	    saveButton.setIcon("icons/save.png");  
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				formBase.validate();
				List<Record> recordList = null;
				if(repairCodeType.getValue().equals(RepairCodeType.SPARE_CODE)){
					recordList = partSelectPanel.getSelectedRecords();
				}else if(repairCodeType.getValue().equals(RepairCodeType.CARGE)){
					recordList = cargoSelectPanel.getSelectedRecords();
				}
				if(recordList == null || recordList.size()== 0){
					Window.alert(ConstantsUtil.stockConstants.alertForChooseRepairItem());
					return;
				}
				String[] itemIds = new String[recordList.size()];
				for(int i=0; i<itemIds.length; i++){
					itemIds[i] = recordList.get(i).getAttribute("id");
				}
				formBase.setValue("itemIdList", itemIds);
				formBase.saveData();
				ShowWindowTools.showCloseWindow(srcRect, RepairCodeEditWindow.this, -1);
			}
		});  
	    
	    /*IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png");  
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				formBase.reset();
			}
		});*/
	    
	    IButton helpButton = new IButton();
	    helpButton.setIcon("icons/book_help.png");  
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	    
	    buttonPanel.addMember(saveButton);
	    //buttonPanel.addMember(cancelButton);
	    buttonPanel.addMember(helpButton);

	    VLayout tileGrid = new VLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("94%");
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addMember(formBase);
		tileGrid.addMember(tabSet);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("6%");	
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addMember(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid);
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
