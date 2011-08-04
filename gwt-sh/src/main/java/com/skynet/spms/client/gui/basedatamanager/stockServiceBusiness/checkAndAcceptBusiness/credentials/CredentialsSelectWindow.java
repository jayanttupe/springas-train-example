package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class CredentialsSelectWindow extends Window {
	
	public CredentialsSelectWindow(
						String windowTitle, 
						final Rectangle rect, 
						String iconUrl ,
						final DynamicForm mainForm, 
						final ListGrid credentialsDetailListGrid) {
		
		final Window objWindow = this;
		setWidth(800);
		setHeight(300);

		final ListGrid listGrid = new ListGrid();
		listGrid.setCanEdit(false);
		listGrid.setCellHeight(22);
		listGrid.setWidth(600);
		listGrid.setHeight(220);
		listGrid.setMargin(5);
		listGrid.setSelectionType(SelectionStyle.SIMPLE);
		listGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);

		// 获取数据源
		String modeName = "stockServiceBusiness.checkAndAcceptBusiness.credentials";
		String dsName = "credentials_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						ListGridField partCentreLocationField = new ListGridField("partCentreLocation", "备件中心位置");
						ListGridField certificateFileNumberField = new ListGridField("certificateFileNumber", "证书存档编号");
						ListGridField stackPositionCodeField = new ListGridField("stackPositionCode", "存放位置");
						ListGridField partNumberField = new ListGridField("partNumber", "件号");
						ListGridField partSerialNumberField = new ListGridField("partSerialNumber", "序号/批号");
						ListGridField remarkField = new ListGridField("remark", "备注");
						listGrid.setFields(partCentreLocationField
								,certificateFileNumberField
								,stackPositionCodeField
								,partNumberField
								,partSerialNumberField
								,remarkField
								);
					}
				});
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("选择");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Record record = listGrid.getSelectedRecord();
				FormItem[] formItems = mainForm.getFields();
				for (FormItem formItem : formItems)
				{
					// 证书ID
					if (formItem.getName().contains("credentialsID"))
					{
						formItem.setValue(record.getAttribute("id"));
					}
					// 证书存档编号
					if (formItem.getName().contains("certificateFileNumber"))
					{
						formItem.setValue(record.getAttribute("certificateFileNumber"));
					}
				}
				// 刷新证书明细数据
				String credentialsID = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("credentialsID", credentialsID);
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				credentialsDetailListGrid.filterData(criteria);
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout buttonLayout = new BtnsHLayout(); 
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(returnButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(listGrid);
		layout.addMember(buttonLayout);
		layout.draw();

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}
