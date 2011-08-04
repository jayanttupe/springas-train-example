package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.containerBusiness;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContainerDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 * @param updateFlg
	 * @param isView
	 */
	public ContainerDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(500);
		setHeight(300);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(280);
		mainForm.setHeight("90%");
		mainForm.setColWidths(80, 200);
		mainForm.setDataSource(listGrid.getDataSource());

		// 容器编号
		final TextItem containerNumber = new TextItem("containerNumber");
		containerNumber.setWidth(145);
		containerNumber.setDefaultValue("业务编号系统自动生成");
		containerNumber.setDisabled(true);
		// 容器类型
		final SelectItem containerType = new SelectItem("containerType");
		containerType.setWidth(145);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.StockContainerType", containerType);
		containerType.setHint("<font color = 'red'>*</font>");
		// 材质
		final SelectItem containerMaterial = new SelectItem("containerMaterial");
		containerMaterial.setWidth(145);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial", containerMaterial);
		// 所属库房
		final SelectItem cmbStockRoomNumber = new SelectItem("stockRoomID");
		cmbStockRoomNumber.setWidth(195);
		cmbStockRoomNumber.setValueField("id");
		cmbStockRoomNumber.setDisplayField("stockRoomChineseName");
		cmbStockRoomNumber.setHint("<font color='red'>*</font>");
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		cmbStockRoomNumber.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		// 获取库房数据
		String stockRoomModeName = "stockServiceBusiness.basicData.stockRoom";
		String stockRoomDSName = "stockRoom_dataSource";
		DataSourceTool stockRoomDST = new DataSourceTool();
		stockRoomDST.onCreateDataSource(stockRoomModeName, stockRoomDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbStockRoomNumber.clearValue();
						cmbStockRoomNumber.setOptionDataSource(dataSource);
						
						if (updateFlg == true)
						{
							final Record record = listGrid.getSelectedRecord();
							mainForm.editRecord(record);
						}
					}
				});
		// 备注
		final TextAreaItem remark = new TextAreaItem("remark");
		remark.setWidth(195);
		remark.setHeight(50);

		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(containerType.getAttribute("Value").equals("") ||
						containerType.getAttribute("Value") == null){
					SC.say("容器类型不能为空!");
					return;
				}
				if(cmbStockRoomNumber.getAttribute("Value").equals("") ||
						cmbStockRoomNumber.getAttribute("Value") == null){
					SC.say("所属库房不能为空!");
					return;
				}
				mainForm.setValue("stockRoomName", cmbStockRoomNumber.getDisplayValue());
				mainForm.saveData();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("返回");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(containerNumber
						,containerType
						,containerMaterial
						,cmbStockRoomNumber
						,remark
						);

		final HLayout buttonLayout = new HLayout();
		buttonLayout.setMargin(5);
		buttonLayout.setMembersMargin(5);
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			saveButton.setVisible(false);
			cancelButton.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}