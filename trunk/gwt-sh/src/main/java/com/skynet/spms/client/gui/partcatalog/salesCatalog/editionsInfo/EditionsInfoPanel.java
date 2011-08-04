package com.skynet.spms.client.gui.partcatalog.salesCatalog.editionsInfo;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditionsInfoPanel extends VLayout {
	
	private DynamicForm form;
	public EditionsInfoPanel(String moduleName,String dsName) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				initPanel(dataSource);
			}
		});
	}
	public void fetchData(String saleReleaseId){
		Criteria criteria = new Criteria("id",saleReleaseId);
		form.fetchData(criteria,new DSCallback() {	
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record record = response.getData()[0];
				form.getItem("id").setValue(record.getAttribute("id"));
				form.getItem("m_EditionsInformation.id").setValue(record.getAttribute("m_EditionsInformation.id"));
				form.getItem("releaseVersion").setValue(record.getAttribute("releaseVersion"));
				form.getItem("edition").setValue(record.getAttribute("edition"));
				form.getItem("m_EditionsInformation.releaseVersionDate").setValue(record.getAttribute("m_EditionsInformation.releaseVersionDate"));
				form.getItem("m_EditionsInformation.releaseMan").setValue(record.getAttribute("m_EditionsInformation.releaseMan"));
				form.getItem("m_EditionsInformation.editionsReviseDate").setValue(record.getAttribute("m_EditionsInformation.editionsReviseDate"));
				form.getItem("m_EditionsInformation.editionsReviseMan").setValue(record.getAttribute("m_EditionsInformation.editionsReviseMan"));
			}
		});
	}
	public void initPanel(DataSource dataSource) {
		form = new DynamicForm();
		form.setDataSource(dataSource);
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
		tiEditionsNumber.setWidth(200);
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
	}
	public void clearFormValues(){
		form.clearValues();
	}
	
	/*HLayout buttonPanel = new HLayout();

	IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
	saveButton.addClickHandler(new ClickHandler() {	
		@Override
		public void onClick(ClickEvent event) {
			form.saveData();
		}
	});	
	IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	cancelButton.addClickHandler(new ClickHandler() {	
		@Override
		public void onClick(ClickEvent event) {
			form.reset();
		}
	});	
	
	//历史版次信息按钮本期不实现
	IButton  historyButton = new IButton(ConstantsUtil.buttonConstants.historyEditionButton());
	buttonPanel.addMember(saveButton);
	buttonPanel.addMember(cancelButton);
	
	buttonPanel.addMember(historyButton);
    this.addMember(buttonPanel);*/
	
}
