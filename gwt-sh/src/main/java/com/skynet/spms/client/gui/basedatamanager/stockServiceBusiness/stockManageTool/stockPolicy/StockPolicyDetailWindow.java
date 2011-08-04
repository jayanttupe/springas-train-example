package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockPolicy;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class StockPolicyDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param srcRect
	 * @param listGrid
	 * @param iconUrl
	 */
	public StockPolicyDetailWindow(String windowTitle, 
							boolean isMax,
							final Rectangle rect,
							StockPolicyListgrid listGrid,
							String iconUrl,
							Boolean updateFlg) {
		final Window objWindow = this;
		setWidth(480);
		setHeight(310);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(280);
		mainForm.setHeight("90%");
		mainForm.setColWidths(80,200);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 库存策略编号
		final TextItem txtStockPolicyNumber = new TextItem("stockPolicyNumber", "库存策略编号");
		// 库存策略描述
		final TextItem txtStockRoomNumber = new TextItem("stockPolicyName", "库存策略描述");
		txtStockRoomNumber.setValueMap("1","2","3");
		// 业务类型
		final SelectItem txtBusinessType = (SelectItem)listGrid.getDataInfo().getFieldInfoByName("businessType").generFormField();
		txtBusinessType.setName("businessType");
		txtBusinessType.setDefaultToFirstOption(true);
		// 备件类别
		final SelectItem txtBackType = new SelectItem("backType", "备件类别");
        txtBackType.setValueMap("消耗件","周转件");
        
        // 是否保税
        RadioGroupItem txtIsBonded = new RadioGroupItem("isBonded","是否保税");  
		 txtIsBonded.setDefaultValue("是");  
		 txtIsBonded.setValueMap("是","否");
		 txtIsBonded.setVertical(false);
		
		// 可入库房
		final SelectItem txtStartCargoSpaceNumber = new SelectItem("stockRoomNumber", "可入库房");
		
		// 取得库房数据
		String headModeName = "stockServiceBusiness.basicData.stockRoom";
		String headDSName = "stockRoom_dataSource";
		DataSourceTool headDST = new DataSourceTool();
		headDST.onCreateDataSource(headModeName, headDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
				
					txtStartCargoSpaceNumber.setOptionDataSource(dataSource);	                
					txtStartCargoSpaceNumber.setDisplayField("stockRoomNumber");
					txtStartCargoSpaceNumber.setValueField("stockRoomNumber");
				
				}
			});
		
		// 可入区域
		final SelectItem txtEndCargoSpaceNumber = new SelectItem("stockAreaCode", "可入区域");
		txtEndCargoSpaceNumber.setValueMap("001","002","003");
		// 备件中心代码
		final SelectItem txtPartCenterCode = new SelectItem("partCenterCode", "备件中心代码");
		txtPartCenterCode.setValueMap("1","2","3");
		// 备件中心代码
		final TextItem txtMemo = new TextItem("memo", "备件中心代码");
	
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData();
				mainForm.clearValues();
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

		mainForm.setFields(txtPartCenterCode
				        ,txtStockPolicyNumber
						,txtStockRoomNumber
						,txtIsBonded
						,txtBusinessType
						,txtBackType							
						,txtStartCargoSpaceNumber
						,txtEndCargoSpaceNumber					
						);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}