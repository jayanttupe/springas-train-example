package com.skynet.spms.client.gui.partcatalog.repairableCatalog.repairData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RepairDataModifyWindow extends BaseWindow{

	public RepairDataModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		RepairDataListGrid repairDataListGrid=(RepairDataListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, RepairDataModifyWindow.this, -1);
			}
		});
		
		final DynamicForm form = new DynamicForm();
		form.setDataSource(repairDataListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(600);
		form.setNumCols(6); 
		form.setColWidths(120,100,40,80,140,120); 
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		DataInfo dataInfo = repairDataListGrid.getDataInfo();
		String required = "<font color=red>*</font>";
		final Record record = repairDataListGrid.getSelectedRecord();
		
		//件号索引ID
		HiddenItem hdnPartIndexId = new HiddenItem("partIndexId");
		hdnPartIndexId.setValue(repairDataListGrid.getPartIndexId());
		itemList.add(hdnPartIndexId);
		
		//备件修理数据ID
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		itemList.add(hdnId);
		
		//参考修理工时
		TextItem tiReferenceProcessingTime = new TextItem("referenceProcessingTime"); 
		tiReferenceProcessingTime.setValue(record.getAttribute("referenceProcessingTime"));
		tiReferenceProcessingTime.setWidth(100);
		tiReferenceProcessingTime.setHint(required);
		tiReferenceProcessingTime.setRequired(true);
		tiReferenceProcessingTime.setValidateOnChange(true);
		itemList.add(tiReferenceProcessingTime);
		//单位代码紧跟参考修理工时后
		SelectItem siUnitCode1 = (SelectItem)dataInfo.getFieldInfoByName("m_UnitCode").generFormField();  
		siUnitCode1.setValue(record.getAttribute("m_UnitCode"));
		siUnitCode1.setWidth(70);
		itemList.add(siUnitCode1);
		
		
		//车间平均维修时间
		TextItem tiMeanShopProcessingTime = new TextItem("meanShopProcessingTime"); 
		tiMeanShopProcessingTime.setValue(record.getAttribute("meanShopProcessingTime"));
		tiMeanShopProcessingTime.setWidth(100);
		itemList.add(tiMeanShopProcessingTime);
		
		//单位代码紧跟参考修理工时后
		/*SelectItem siUnitCode = (SelectItem)dataInfo.getFieldInfoByName("m_UnitCode").generFormField();
		siUnitCode.setWidth(80);
		itemList.add(siUnitCode);*/
		
		//价格类型代码
		SelectItem siPriceTypeCode = (SelectItem)dataInfo.getFieldInfoByName("m_PriceTypeCode").generFormField();
		siPriceTypeCode.setValue(record.getAttribute("m_PriceTypeCode"));
		siPriceTypeCode.setWidth(220);
		siPriceTypeCode.setColSpan(3);
		siPriceTypeCode.setHint(required);
		siPriceTypeCode.setRequired(true);
		itemList.add(siPriceTypeCode);
		
		//设备可交换
		RadioGroupItem rgiExchangeUnitAvailableIndicator = new RadioGroupItem("m_ExchangeUnitAvailableIndicator");
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		valueMap.put("YES", ConstantsUtil.commonConstants.choiceYes());
		valueMap.put("NO", ConstantsUtil.commonConstants.choiceNo());
		rgiExchangeUnitAvailableIndicator.setValueMap(valueMap);
		rgiExchangeUnitAvailableIndicator.setValue(record.getAttribute("m_ExchangeUnitAvailableIndicator"));
		rgiExchangeUnitAvailableIndicator.setWidth(100);
		rgiExchangeUnitAvailableIndicator.setVertical(false);
		itemList.add(rgiExchangeUnitAvailableIndicator);
		
		//修理工作类别
		SelectItem siRepairProcessCodes = (SelectItem)dataInfo.getFieldInfoByName("m_RepairProcessCodes").generFormField();  
		siRepairProcessCodes.setValue(record.getAttribute("m_RepairProcessCodes"));
		siRepairProcessCodes.setWidth(220);
		siRepairProcessCodes.setColSpan(3);
		siRepairProcessCodes.setHint(required);
		siRepairProcessCodes.setRequired(true);
		itemList.add(siRepairProcessCodes);
		
		//飞机系统代码
		SelectItem siAircraftSystemCode = (SelectItem)dataInfo.getFieldInfoByName("m_AircraftSystemCode").generFormField(); 
		siAircraftSystemCode.setValue(record.getAttribute("m_AircraftSystemCode"));
		siAircraftSystemCode.setWidth(100);
		itemList.add(siAircraftSystemCode);
		
		//承修商代码
		final SelectItem siRepairShopCode = new CustomSelectItem(
				"repairCodeId",
				"partCatalog.repairable",
				"repairShopCodeForm_dataSource",
				"repairShopCodeId",
				"repairShopCode",
				"repairShopCode",
				"repairShopName");
		siRepairShopCode.setPickListWidth(400);
        siRepairShopCode.setWidth(220);
		siRepairShopCode.setColSpan(5);
		siRepairShopCode.setHint(required);
		siRepairShopCode.setRequired(true);
		siRepairShopCode.setValue(record.getAttribute("m_RepairShopCode.id"));
		itemList.add(siRepairShopCode);
		
		//维修依据文件
        TextAreaItem taiRepairProcessRemarks = new TextAreaItem("repairProcessRemarks");
        taiRepairProcessRemarks.setValue(record.getAttribute("repairProcessRemarks"));
        taiRepairProcessRemarks.setWidth(480);  
        taiRepairProcessRemarks.setRowSpan(3); 
        taiRepairProcessRemarks.setColSpan(5);
        itemList.add(taiRepairProcessRemarks);
        //备注
        TextAreaItem taiRemark = new TextAreaItem("remarkText");
        taiRemark.setValue(record.getAttribute("remarkText"));
        taiRemark.setWidth(480);  
        taiRemark.setRowSpan(3); 
        taiRemark.setColSpan(5);
        itemList.add(taiRemark);
       
        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        
		HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//form.setValue("repairCodeId", (String)siRepairShopCode.getValue());
				if(form.validate()){
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, RepairDataModifyWindow.this, -1);
			}
			}
		});
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.reset();
			}
		});
	    IButton helpButton = new IButton(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.setIcon("icons/book_help.png");
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);

		HLayout tileGrid = new HLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");
		
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addChild(buttonPanel);
		
		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		
		return vLayout;
		
	}
}