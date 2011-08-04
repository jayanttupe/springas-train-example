package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult;

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
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class StockCheckResultDetailWindow extends Window{

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
	public StockCheckResultDetailWindow(String windowTitle, 
									boolean isMax,
									final Rectangle rect,
									final StockCheckResultListgrid listGrid, 
									String iconUrl,
									final Boolean updateFlg,
									final Boolean isView) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(485);
		
		//基本信息
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(480);
		mainForm.setNumCols(4);
		mainForm.setColWidths(90, 150, 90, 150);

		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 盘点ID
		final HiddenItem txtStockCheckID = new HiddenItem("id");
		// 盘点编号
		final TextItem txtCheckNumber = new TextItem("checkNumber");
		txtCheckNumber.setWidth(150);
        txtCheckNumber.setDisabled(true);
        // 盘点项目描述
		final TextItem txtCheckDescribe = new TextItem("checkDescribe");
		txtCheckDescribe.setWidth(150);
        // 盘点开始日期
		final CustomDateItem txtCheckStartDate = new CustomDateItem("checkStartDate");
		txtCheckStartDate.setWidth(150);
		// 盘点结束日期
		final CustomDateItem txtCheckEndDate = new CustomDateItem("checkEndDate");
		txtCheckEndDate.setWidth(150);
        // 起始货位
		final TextItem txtFromCargoSpaceNumber = new TextItem("fromCargoSpaceNumber");
		txtFromCargoSpaceNumber.setMask(">CCC-CC-CCC-CCC-CC");
		txtCheckDescribe.setWidth(150);
        // 结束货位
		final TextItem txtToCargoSpaceNumber = new TextItem("toCargoSpaceNumber");
		txtToCargoSpaceNumber.setMask(">CCC-CC-CCC-CCC-CC");
		txtCheckDescribe.setWidth(150);
		// 备注
		final TextAreaItem txtremark = new TextAreaItem("remark");
		txtremark.setColSpan(3);
		txtremark.setWidth(400);
		txtremark.setHeight(60);

		// 盘点明细数据
		final StockCheckResultItemListgrid stockCheckItemListgrid = new StockCheckResultItemListgrid();
		stockCheckItemListgrid.setAutoSaveEdits(false);
		stockCheckItemListgrid.setHeight(250);
		stockCheckItemListgrid.setWidth(600);
		stockCheckItemListgrid.setMargin(5);
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResult";
		String detaildsName = "stockCheckResultItem_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {						
		    			stockCheckItemListgrid.setDataSource(dataSource);
						stockCheckItemListgrid.drawStockCheckResultItemManageListgrid();
						
						if (updateFlg == true) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("stockCheckID", "" + txtStockCheckID.getValue().toString());
							stockCheckItemListgrid.fetchData(criteria);
						}
					}
				});

		mainForm.setFields(txtStockCheckID,
				txtCheckNumber,
				txtCheckDescribe,
				txtCheckStartDate,
				txtCheckEndDate,
				txtFromCargoSpaceNumber,
				txtToCargoSpaceNumber,
				txtremark
				);

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		final BtnsHLayout bottomBtnLayout = new BtnsHLayout();
		bottomBtnLayout.addMember(btnReturn);

		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.setMembersMargin(5);
        vLayout.addMember(mainForm);
        vLayout.addMember(stockCheckItemListgrid);
        vLayout.addMember(bottomBtnLayout);
        
        if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
        	btnReturn.setVisible(false);
        }
        
        SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}