package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.editionsInformation;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditionsInformationPanel extends VLayout{

	private DynamicForm form;
	private ListGrid priceListGrid;
	
	public EditionsInformationPanel(ListGrid priceListGrid){
		this.priceListGrid = priceListGrid;
		form = new DynamicForm();
		form.setDataSource(priceListGrid.getDataSource());
		form.setWidth(320);
		form.setPadding(5);
		form.setColWidths(120,200);
		
		List<FormItem> itemList = new ArrayList<FormItem>();
		//价格索引ID
		HiddenItem hdnId = new HiddenItem("id");
		itemList.add(hdnId);
		//版次信息ID
		HiddenItem hdneditionId = new HiddenItem("m_EditionsInformation.id");
		itemList.add(hdneditionId);
		//版本号
		TextItem tiVersionNumber = new TextItem("releaseVersion");
		tiVersionNumber.setWidth(200);
		itemList.add(tiVersionNumber);
		//版次
		TextItem tiEditionsNumber = new TextItem("edition");
		tiVersionNumber.setWidth(200);
		itemList.add(tiEditionsNumber);
		//版本发布日期
		TextItem tiReleaseVersionDate = new TextItem("m_EditionsInformation.releaseVersionDate"); 
		tiReleaseVersionDate.setWidth(200);
		itemList.add(tiReleaseVersionDate);
		//版本发布人
		TextItem tiReleaseMan = new TextItem("m_EditionsInformation.releaseMan");
		tiReleaseMan.setWidth(200);
		itemList.add(tiReleaseMan);
		//版次修订日期
		TextItem tiEditionsReviseDate = new TextItem("m_EditionsInformation.editionsReviseDate");  
		tiEditionsReviseDate.setWidth(200);
		itemList.add(tiEditionsReviseDate);
		//版次修订人
		TextItem tiEditionsReviseMan = new TextItem("m_EditionsInformation.editionsReviseMan");
		tiEditionsReviseMan.setWidth(200);
		itemList.add(tiEditionsReviseMan);
		
		FormItem[] items = new FormItem[itemList.size()];
	    itemList.toArray(items); 
	    form.setFields(items);
	
		this.addMember(form);
		
		/*HLayout buttonPanel = new HLayout();
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		
		IButton helpButton = new IButton("帮助");
		
		IButton showHisroryButton = new IButton("历史版次信息");
		
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);
		buttonPanel.addMember(showHisroryButton);
		
		this.addMember(buttonPanel);*/
	}
	
	public void fetchData(){
		Record record = priceListGrid.getSelectedRecord();
		form.getItem("id").setValue(record.getAttribute("id"));
		form.getItem("m_EditionsInformation.id").setValue(record.getAttribute("m_EditionsInformation.id"));
		form.getItem("releaseVersion").setValue(record.getAttribute("releaseVersion"));
		form.getItem("edition").setValue(record.getAttribute("edition"));
		form.getItem("m_EditionsInformation.releaseVersionDate").setValue(record.getAttribute("m_EditionsInformation.releaseVersionDate"));
		form.getItem("m_EditionsInformation.releaseMan").setValue(record.getAttribute("m_EditionsInformation.releaseMan"));
		form.getItem("m_EditionsInformation.editionsReviseDate").setValue(record.getAttribute("m_EditionsInformation.editionsReviseDate"));
		form.getItem("m_EditionsInformation.editionsReviseMan").setValue(record.getAttribute("m_EditionsInformation.editionsReviseMan"));
	}
	
}
