package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApply;

import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
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
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class StockMoveApplyDetailWindow extends Window{

	private int itemNo=0;
	private float amount=0;
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
	public StockMoveApplyDetailWindow(String windowTitle, 
									boolean isMax,
									final Rectangle rect, 
									ListGrid listGrid, 
									String iconUrl, 
									Boolean updateFlg,
									final Boolean isView){

		final Window objWindow = this;
		setWidth(650);
		setHeight(490);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(440);
		mainForm.setNumCols(4);
		mainForm.setColWidths(70, 150, 70, 150);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// ID
		final HiddenItem txtStockMovingRecordID = new HiddenItem("id");
		// 移库单号
		final TextItem txtStockMovingNumber = new TextItem("stockMovingNumber");
		txtStockMovingNumber.setDisabled(true);
		txtStockMovingNumber.setDefaultValue("业务编号自动生成");
		txtStockMovingNumber.setColSpan(3);
		// 移出库房
		final SelectItem txtMovingOutStockRoomNumbers = new SelectItem("movingOutStockRoomNumbers");
		// 移入库房
		final SelectItem txtMovingInStockRoomNumbers = new SelectItem("movingInStockRoomNumbers");
		txtMovingOutStockRoomNumbers.setDisplayField("stockRoomChineseName");
		txtMovingOutStockRoomNumbers.setValueField("stockRoomNumber");
		txtMovingInStockRoomNumbers.setDisplayField("stockRoomChineseName");
		txtMovingInStockRoomNumbers.setValueField("stockRoomNumber");
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		stockRoomNumberField.setCanEdit(false);
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		stockRoomChineseNameField.setCanEdit(false);
		txtMovingOutStockRoomNumbers.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		txtMovingInStockRoomNumbers.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		// 总项数
		final TextItem txtTotalItemsQuantity = new TextItem("totalItemsQuantity");
		txtTotalItemsQuantity.setDisabled(true);
		// 总金额
		final TextItem txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount.setDisabled(true);
		// 批准人
		final TextItem txtRatifier = new TextItem("ratifier");
		// 批准日期
		final CustomDateItem txtRatifyDate = new CustomDateItem("ratifyDate");
		// 移库原因
		final TextAreaItem txtStockMovingReason = new TextAreaItem("stockMovingReason");
		txtStockMovingReason.setColSpan(3);
		txtStockMovingReason.setWidth(370);
		txtStockMovingReason.setHeight(50);
		
		// 取得库房数据
		String headModeName = "stockServiceBusiness.basicData.stockRoom";
		String headDSName = "stockRoom_dataSource";
		DataSourceTool headDST = new DataSourceTool();
		headDST.onCreateDataSource(headModeName, headDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					txtMovingOutStockRoomNumbers.clearValue();
					txtMovingInStockRoomNumbers.clearValue();
					txtMovingOutStockRoomNumbers.setOptionDataSource(dataSource);
					txtMovingInStockRoomNumbers.setOptionDataSource(dataSource);	                
					
				}
			});
		
		mainForm.setFields(txtStockMovingRecordID
				,txtStockMovingNumber
				,txtMovingOutStockRoomNumbers
				,txtMovingInStockRoomNumbers
				,txtTotalItemsQuantity
				,txtTotalAmount
				,txtRatifier
				,txtRatifyDate
				,txtStockMovingReason
				);					    
		
		if(updateFlg==true){
			txtStockMovingNumber.setDisabled(true);
		}
		
		final StockMoveApplyItemListgrid stockMoveApplyItemListgrid = new StockMoveApplyItemListgrid();;
		stockMoveApplyItemListgrid.setMargin(5);
		stockMoveApplyItemListgrid.setAutoSaveEdits(false);
		//明细Grid
		stockMoveApplyItemListgrid.setHeight(220);
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveOutRecord";
		String detaildsName = "stockMovingRecordItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {					
		    			stockMoveApplyItemListgrid.setDataSource(dataSource);
						stockMoveApplyItemListgrid.drawStockMoveItemsManageListgrid();
						if(txtStockMovingRecordID.getValue() != null) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("stockMovingRecordID", "" + txtStockMovingRecordID.getValue().toString());
							stockMoveApplyItemListgrid.filterData(criteria);
						}
						
						if (isView == true) {
							stockMoveApplyItemListgrid.setCanEdit(false);
						} else {
							stockMoveApplyItemListgrid.setCanEdit(true);	
						}
				}
			});
		
		final IButton btnNew = new IButton();
		btnNew.setTitle("新增");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		
				Map map = new HashMap();
				Integer stockMovingNumberItem = stockMoveApplyItemListgrid.getTotalRows() + 1;
				map.put("stockMovingNumberItem", stockMovingNumberItem);
				stockMoveApplyItemListgrid.startEditingNew(map);
				txtTotalItemsQuantity.setValue(itemNo+1);
			}
		});	
		stockMoveApplyItemListgrid.addEditorExitHandler(new EditorExitHandler() {
			
			@Override
			public void onEditorExit(EditorExitEvent event) {
				txtTotalAmount.setValue(amount+stockMoveApplyItemListgrid.getEditValueAsFloat(stockMoveApplyItemListgrid.getTotalRows()+1, "totalAmount"));
			}
		});
		final IButton btnDelete = new IButton();
		btnDelete.setTitle("删除");
		btnDelete.setWidth(65);
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				stockMoveApplyItemListgrid.removeSelectedData();
			}
		});
		
		IButton backButton = new IButton();
		backButton.setTitle("返回");
		backButton.setWidth(65);
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 设置库房的逻辑库明细数据
			ListGridRecord[] detailRecord = new ListGridRecord[stockMoveApplyItemListgrid.getTotalRows()];
			for (int i = 0; i < stockMoveApplyItemListgrid.getTotalRows(); i++)
			{
				detailRecord[i] = (ListGridRecord)stockMoveApplyItemListgrid.getEditedRecord(i);
			}
			mainForm.setValue("stockMovingRecordItems", detailRecord);
			
			mainForm.saveData(new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					String stockMovingRecordID = response.getData()[0].getAttribute("id");
					txtStockMovingRecordID.setValue(stockMovingRecordID);
					SC.say("保存成功！");
					}
				});
			}
		});			
				
		final BtnsHLayout detailBtnLayout = new BtnsHLayout();
		detailBtnLayout.addMember(btnNew);
		detailBtnLayout.addMember(btnDelete);
		
		final BtnsHLayout bottomBtnLayout = new BtnsHLayout();
		bottomBtnLayout.addMember(saveButton);
		bottomBtnLayout.addMember(backButton);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailBtnLayout);
		detailVLayout.addMember(stockMoveApplyItemListgrid);
		
		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(mainForm);
		vLayout.addMember(detailVLayout);
		vLayout.addMember(bottomBtnLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			detailBtnLayout.setVisible(false);
			saveButton.setVisible(false);
			backButton.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);

	}
}
