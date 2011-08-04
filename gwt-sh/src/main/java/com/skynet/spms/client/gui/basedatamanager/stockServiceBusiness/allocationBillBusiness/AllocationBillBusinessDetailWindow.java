package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class AllocationBillBusinessDetailWindow extends Window {

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
	public AllocationBillBusinessDetailWindow(String windowTitle, 
											boolean isMax,
											final Rectangle rect, 
											final ListGrid listGrid, 
											String iconUrl, 
											final Boolean updateFlg,
											final Boolean isView) {

		final Window objWindow = this;
		setWidth(700);
		setHeight(495);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(500);
		mainForm.setPadding(5);
		mainForm.setNumCols(4);
		mainForm.setDataSource(listGrid.getDataSource());

		// ID
		final HiddenItem txtAllocationBillID = new HiddenItem("id");
		// 调拨单编号
		final TextItem txtAllocationBillNumber = new TextItem("allocationBillNumber");
		txtAllocationBillNumber.setDisabled(true);
		// 源单据类型
		final TextItem txtSourceBillType = new TextItem("sourceBillType");
		// 收货库房
		final SelectItem txtInWareHouse = new SelectItem("inWareHouse");
		txtInWareHouse.setValueField("id");
		txtInWareHouse.setDisplayField("stockRoomChineseName");
		txtInWareHouse.setHint("<font color='red'>*</font>");
		ListGridField instockRoomNumberField = new ListGridField("stockRoomNumber");
		ListGridField instockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		txtInWareHouse.setPickListFields(instockRoomNumberField, instockRoomChineseNameField);
		// 发货库房
		final SelectItem txtOutWareHouse = new SelectItem("outWareHouse");
		txtOutWareHouse.setValueField("id");
		txtOutWareHouse.setDisplayField("stockRoomChineseName");
		txtOutWareHouse.setHint("<font color='red'>*</font>");
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		txtOutWareHouse.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		// 获取库房数据
		String stockRoomModeName = "stockServiceBusiness.basicData.stockRoom";
		String stockRoomDSName = "stockRoom_dataSource";
		DataSourceTool instockRoomDST = new DataSourceTool();
		instockRoomDST.onCreateDataSource(stockRoomModeName, stockRoomDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						txtInWareHouse.clearValue();
						txtOutWareHouse.clearValue();
						
						txtInWareHouse.setOptionDataSource(dataSource);
						txtOutWareHouse.setOptionDataSource(dataSource);
						
						if (updateFlg == true)
						{
							final Record record = listGrid.getSelectedRecord();
							mainForm.editRecord(record);
						}
					}
				});
		// 备注
		final TextAreaItem txtRemark = new TextAreaItem("remark");
		txtRemark.setWidth(400); 
		txtRemark.setHeight(50);
		txtRemark.setColSpan(4);
		// 审核人
		final TextItem txtCheckUser = new TextItem("checkUser");
		// 制单人
		final TextItem txtCreateUser = new TextItem("createUser");
		// 业务类型
		final SelectItem txtBusinessType = new SelectItem("businessType");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.b.BusinessType", txtBusinessType);
		// 调拨日期
		final CustomDateItem txtBusinessDate = new CustomDateItem("businessDate");
		txtBusinessDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 审核日期
		final CustomDateItem txtCheckDate = new CustomDateItem("checkDate");
		txtCheckDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 制单日期
		final CustomDateItem txtCreateByDate = new CustomDateItem("createByDate");
		txtCreateByDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 航材调拨管理明细
		final AllocationGoodListgrid allocationGoodListgrid = new AllocationGoodListgrid();
		allocationGoodListgrid.setWidth100();
		allocationGoodListgrid.setHeight(180);
		allocationGoodListgrid.setMargin(5);
		allocationGoodListgrid.setAutoSaveEdits(false);
		allocationGoodListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取航材调拨管理明细数据
		String detailModeName = "stockServiceBusiness.partsInventory.allocationBillBusiness";
		String detailDSName = "allocationGood_dataSource";
		DataSourceTool businessDST = new DataSourceTool();
		businessDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						allocationGoodListgrid.setDataSource(dataSource);
						
						allocationGoodListgrid.drawAllocationGoodListgrid();
						allocationGoodListgrid.setCanEdit(true);
						allocationGoodListgrid.setEditEvent(ListGridEditEvent.CLICK);
						if (txtAllocationBillID.getValue() != null)
						{
							allocationGoodListgrid.fetchData(new Criteria("allocationBillID", "" + txtAllocationBillID.getValue().toString()));
						}
						
						if (isView == true) {
							allocationGoodListgrid.setCanEdit(false);
						} else {
							allocationGoodListgrid.setCanEdit(true);
						}
					}
			});

		final IButton addButton = new IButton();
		addButton.setTitle("新增");
		addButton.setWidth(65);
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				allocationGoodListgrid.startEditingNew();
			}
		});
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 设置明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[allocationGoodListgrid.getTotalRows()];
				for (int i = 0; i < allocationGoodListgrid.getTotalRows(); i++)
				{
					detailRecord[i] = (ListGridRecord)allocationGoodListgrid.getEditedRecord(i);
				}
				mainForm.setValue("allocationGood", detailRecord);
				mainForm.setValue("inWareHouseName", txtInWareHouse.getDisplayValue());
				mainForm.setValue("outWareHouseName", txtOutWareHouse.getDisplayValue());
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						String allocationBillID = response.getData()[0].getAttribute("id");
						txtAllocationBillID.setValue(allocationBillID);
						txtAllocationBillNumber.setValue(response.getData()[0].getAttribute("allocationBillNumber"));

						// 刷新区域数据
						Criteria detailCriteria = new Criteria();
						detailCriteria.addCriteria("temp", String.valueOf(Math.random()));
						detailCriteria.addCriteria("allocationBillID", "" + txtAllocationBillID.getValue().toString());
						DataSource businessDS = allocationGoodListgrid.getDataSource();
						allocationGoodListgrid.setDataSource(businessDS);
						allocationGoodListgrid.drawAllocationGoodListgrid();
						allocationGoodListgrid.fetchData(detailCriteria);
					}
				});
				mainForm.clearValues();
				SC.say("保存成功！");
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

		mainForm.setFields(txtAllocationBillID
						,txtAllocationBillNumber
				      	,txtBusinessType
				      	,txtSourceBillType
				      	,txtBusinessDate		
				      	,txtInWareHouse
				      	,txtOutWareHouse		
				      	,txtRemark
						,txtCheckUser
						,txtCheckDate
						,txtCreateUser					
						,txtCreateByDate
						);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		final BtnsHLayout addButtonLayout = new BtnsHLayout();
		addButtonLayout.addMember(addButton);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(addButtonLayout);
		detailVLayout.addMember(allocationGoodListgrid);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(detailVLayout);
		layout.addMember(buttonLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			addButtonLayout.setVisible(false);
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