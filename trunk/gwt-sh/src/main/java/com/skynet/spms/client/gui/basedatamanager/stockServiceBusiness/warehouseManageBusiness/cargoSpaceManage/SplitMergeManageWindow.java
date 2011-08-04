package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class SplitMergeManageWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public SplitMergeManageWindow(String windowTitle,
								boolean isMax,
								final Rectangle rect,
								final ListGrid listgrid,
								String iconUrl) {
		final Window objWindow = this;
		setWidth(790);
		setHeight(480);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(590);
		mainForm.setNumCols(7);
		mainForm.setColWidths(55,130,50,70,100,70,50);
		mainForm.setHeight("90%");

		final TextItem cargoSpaceMetre = new TextItem("cargoSpaceMetre", "货位格号");
		cargoSpaceMetre.setWidth(130);
		cargoSpaceMetre.setMask(">CCC-CC-CCC-CCC");
		
		final IButton btnQuery = new IButton();
		btnQuery.setTitle("查询");
		btnQuery.setWidth(65);

		CanvasItem canvasQuery = new CanvasItem();
		canvasQuery.setShowTitle(false);
		canvasQuery.setCanvas(btnQuery);
		
		final RadioGroupItem splitMerge = new RadioGroupItem("splitMerge", "操作");
		splitMerge.setValueMap("合并", "拆分"); 
		splitMerge.setVertical(false);
		splitMerge.setDefaultValue("合并"); 
        splitMerge.setRedrawOnChange(true);
        splitMerge.setWidth(100);
        
        final TextItem splitTo = new TextItem("拆分为");
        splitTo.setWidth(50);
        splitTo.setLength(2);
        splitTo.setMask("##");
        splitTo.setDisabled(true);
        splitTo.setValidators(ValidateUtils.IntRangeValidator(1, 99));

        splitMerge.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				splitTo.clearValue();
				
				if (event.getValue().equals("合并")) {
					splitTo.setDisabled(true);
				} else {
					splitTo.setDisabled(false);
				}
			}
		});
        
        mainForm.setFields(cargoSpaceMetre,
        					canvasQuery,
        					splitMerge,
        					splitTo
        					);

        final CargoSpaceManagerListgrid cargoSpaceManagerListgrid = new CargoSpaceManagerListgrid();
		cargoSpaceManagerListgrid.setMargin(5);
		cargoSpaceManagerListgrid.setHeight(370);
		cargoSpaceManagerListgrid.setWidth(590);
		
        // 获取数据源
		String modeName = "stockServiceBusiness.basicData.cargoSpace";
		String dsName = "cargoSpace_dataSource";

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cargoSpaceManagerListgrid.setDataSource(dataSource);
						cargoSpaceManagerListgrid.drawCargoSpaceManagerListgrid();
				        cargoSpaceManagerListgrid.getField("stockRoomNumber").setHidden(true);
						cargoSpaceManagerListgrid.getField("stockRoomChineseName").setHidden(true);
						
						cargoSpaceManagerListgrid.setShowFilterEditor(false);
						cargoSpaceManagerListgrid.setSelectionType(SelectionStyle.SIMPLE);
						cargoSpaceManagerListgrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
					}
				});
		
		// ListGrid中的选择事件处理
		cargoSpaceManagerListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					cargoSpaceManagerListgrid.selectRecords(cargoSpaceManagerListgrid.getSelection(), false);
					cargoSpaceManagerListgrid.selectRecord(selectedRecord);
				}else if(cargoSpaceManagerListgrid.getSelection().length == 1){
					selectedRecord = cargoSpaceManagerListgrid.getSelection()[0];
					cargoSpaceManagerListgrid.scrollToRow(cargoSpaceManagerListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 查询处理
		btnQuery.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				if (cargoSpaceMetre.getValue() != null && !cargoSpaceMetre.getValue().equals("")) {
					Criteria criteria = new Criteria();
					criteria.addCriteria("filter", "1");
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("cargoSpaceNumber", "" + cargoSpaceMetre.getDisplayValue());
					cargoSpaceManagerListgrid.fetchData(criteria);	
				} else {
					SC.say("请输入一个货位格号！");
				}
			}
		});
		
		// 拆分、合并处理
		final IButton btnSave = new IButton();
		btnSave.setTitle("确定");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 合并
				if (splitMerge.getValue().equals("合并")) {
					if(cargoSpaceManagerListgrid.getSelection().length > 1){
						// 取得选择行
						ListGridRecord[] selRecords = cargoSpaceManagerListgrid.getSelection();
						final String[] cargoSpaceNumbers = new String[selRecords.length];
						for (int i = 0; i < selRecords.length; i++) {
							cargoSpaceNumbers[i] = selRecords[i].getAttributeAsString("cargoSpaceNumber");
						}

						SC.confirm("确认合并所选择的货位？", new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value == true) {
									Record record = new Record();
									record.setAttribute("merge", "1");
									record.setAttribute("cargoSpaceNumbers", cargoSpaceNumbers);
									cargoSpaceManagerListgrid.updateData(record, new DSCallback() {
										@Override
										public void execute(DSResponse response, Object rawData, DSRequest request) {
											SC.say("合并成功！");

											Criteria criteria = new Criteria();
											criteria.addCriteria("filter", "1");
											criteria.addCriteria("temp", String.valueOf(Math.random()));
											criteria.addCriteria("cargoSpaceNumber", "" + cargoSpaceMetre.getDisplayValue());
											cargoSpaceManagerListgrid.fetchData(criteria);
										}
									});
								}
							}
						});
						
					} else {
						SC.say("至少选择两条记录进行合并！");
						return;
					}
				}
				
				// 拆分
				if (splitMerge.getValue().equals("拆分")) {
					if (cargoSpaceManagerListgrid.getSelection().length == 1) {
						final String splitNumber = splitTo.getValue().toString();
						final String cargoSpaceNumber = cargoSpaceManagerListgrid.getSelectedRecord().getAttributeAsString("cargoSpaceNumber");
						
						SC.confirm("确认将货位" + cargoSpaceNumber + "拆分为" + splitNumber + "个货位？", new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value == true) {
									Record record = new Record();
									record.setAttribute("split", "1");
									record.setAttribute("cargoSpaceNumber", "" + cargoSpaceNumber);
									record.setAttribute("storageRacksCaseNumber", "" + cargoSpaceMetre.getDisplayValue());
									record.setAttribute("newCargoSpaceCount", "" + splitNumber);
									
									cargoSpaceManagerListgrid.updateData(record, new DSCallback() {
										@Override
										public void execute(DSResponse response, Object rawData, DSRequest request) {
											SC.say("拆分成功！");

											Criteria criteria = new Criteria();
											criteria.addCriteria("filter", "1");
											criteria.addCriteria("temp", String.valueOf(Math.random()));
											criteria.addCriteria("cargoSpaceNumber", "" + cargoSpaceMetre.getDisplayValue());
											cargoSpaceManagerListgrid.fetchData(criteria);
										}
									});
								}
							}
						});
					} else {
						SC.say("请选择一条记录进行拆分！");
						return;
					}
				}
			}
		});

		// 返回处理
		IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		final BtnsHLayout bottomHLayout = new BtnsHLayout();
		bottomHLayout.setHeight("10%");
		bottomHLayout.addMember(btnSave);
		bottomHLayout.addMember(btnReturn);
		
		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(mainForm);
		vLayout.addMember(cargoSpaceManagerListgrid);	
		vLayout.addMember(bottomHLayout);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}
