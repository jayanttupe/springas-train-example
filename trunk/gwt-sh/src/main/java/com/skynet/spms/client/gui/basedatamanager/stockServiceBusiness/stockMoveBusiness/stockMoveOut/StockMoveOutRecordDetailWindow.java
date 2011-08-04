package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOut;

import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class StockMoveOutRecordDetailWindow extends Window{

	/**
	 * @param rect
	 * @param stockMoveRecordListgrid
	 */
	public StockMoveOutRecordDetailWindow(String windowTitle, 
										boolean isMax,
										final Rectangle rect, 
										ListGrid listGrid, 
										String iconUrl, 
										Boolean updateFlg){
	
		final Window objWindow = this;
		setWidth(700);
		setHeight(500);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(500);
		mainForm.setNumCols(4);
		mainForm.setColWidths(100, 150, 100, 150);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		final HiddenItem txtStockMovingRecordID = new HiddenItem("id");
		final TextItem txtStockMovingNumber = new TextItem("stockMovingNumber");
		txtStockMovingNumber.setColSpan(3);
		final SelectItem txtMovingOutStockRoomNumbers = new SelectItem("movingOutStockRoomNumbers");
		final SelectItem txtMovingInStockRoomNumbers = new SelectItem("movingInStockRoomNumbers");
		final TextItem txtTotalItemsQuantity = new TextItem("totalItemsQuantity");
		txtTotalItemsQuantity.setDisabled(true);
		final TextItem txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount.setDisabled(true);
		final TextItem txtRatifier = new TextItem("ratifier");
		final CustomDateItem txtRatifyDate = new CustomDateItem("ratifyDate");
		final TextAreaItem txtStockMovingReason = new TextAreaItem("stockMovingReason");
		txtStockMovingReason.setColSpan(3);
		txtStockMovingReason.setWidth(330);
		txtStockMovingReason.setHeight(40);
		
		// 取得库房数据
		String headModeName = "stockServiceBusiness.basicData.stockRoom";
		String headDSName = "stockRoom_dataSource";
		DataSourceTool headDST = new DataSourceTool();
		headDST.onCreateDataSource(headModeName, headDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					dataSource.fetchData();
					txtMovingOutStockRoomNumbers.setOptionDataSource(dataSource);
					txtMovingInStockRoomNumbers.setOptionDataSource(dataSource);
	                
					txtMovingOutStockRoomNumbers.setDisplayField("stockRoomNumber");
					txtMovingOutStockRoomNumbers.setValueField("stockRoomNumber");
					txtMovingInStockRoomNumbers.setDisplayField("stockRoomNumber");
					txtMovingInStockRoomNumbers.setValueField("stockRoomNumber");
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
		
		final StockMoveOutRecordItemListgrid stockMoveOutRecordItemListgrid = new StockMoveOutRecordItemListgrid();;
		stockMoveOutRecordItemListgrid.setAutoSaveEdits(false);
		stockMoveOutRecordItemListgrid.setMargin(5);
		stockMoveOutRecordItemListgrid.setAutoSaveEdits(false);
		//明细Grid
		stockMoveOutRecordItemListgrid.setHeight(220);
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveOutRecord";
		String detaildsName = "stockMovingRecordItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {					
		    			stockMoveOutRecordItemListgrid.setDataSource(dataSource);
						stockMoveOutRecordItemListgrid.drawStockMoveItemsManageListgrid();
						if(txtStockMovingRecordID.getValue() != null) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("stockMovingRecordID", "" + txtStockMovingRecordID.getValue().toString());
							stockMoveOutRecordItemListgrid.filterData(criteria);
						}
				}
			});
		
		final IButton btnNew = new IButton();
		btnNew.setTitle("新增");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer stockMovingNumberItem = stockMoveOutRecordItemListgrid.getTotalRows() + 1;
				String nextItemNO = "";
				Map map = new HashMap();
				Integer len = String.valueOf(stockMovingNumberItem).length();
				if (len == 1) {
					nextItemNO = "00" + String.valueOf(stockMovingNumberItem);
				} else if (len == 2) {
					nextItemNO = "0" + String.valueOf(stockMovingNumberItem);
				} else if (len == 3) {
					nextItemNO = String.valueOf(stockMovingNumberItem);
				}
				map.put("stockMovingNumberItem", nextItemNO);
				stockMoveOutRecordItemListgrid.startEditingNew(map);
			}
		});	
		
		final IButton btnDelete = new IButton();
		btnDelete.setTitle("删除");
		btnDelete.setWidth(65);
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				stockMoveOutRecordItemListgrid.removeSelectedData();
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
			ListGridRecord[] detailRecord = new ListGridRecord[stockMoveOutRecordItemListgrid.getTotalRows()];
			for (int i = 0; i < stockMoveOutRecordItemListgrid.getTotalRows(); i++)
			{
				detailRecord[i] = (ListGridRecord)stockMoveOutRecordItemListgrid.getEditedRecord(i);
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
		
		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(mainForm);
		vLayout.addMember(detailBtnLayout);
		vLayout.addMember(stockMoveOutRecordItemListgrid);
		vLayout.addMember(bottomBtnLayout);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}
