package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 回购申请单添加
 * 
 * @author fl
 * 
 */
public class BuyBackSheetAddWin extends BaseWindow {

	private static I18n ui = new I18n();
	
	public BuyBackSheetAddWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	public BaseListGrid itemGrid;

	public BuyBackSheetItemAddForm backSheetItemForm;

	public SheetModelLocator model = SheetModelLocator.getInstance();
	/**
	 * 回购申请单明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getSheetItemGrid() {
		final BaseListGrid grid = new BaseListGrid() {
			@Override
			public void drawGrid() {
				//数据加载中
				setLoadingDataMessage(ui.getM().msgLoading());
				/**
				 * 件号
				 */
				ListGridField field2 = new ListGridField("partNumber");
				field2.setCanFilter(true);
				/**
				 * 备件描述
				 */
				ListGridField field3 = new ListGridField("remarkText");
				field3.setCanFilter(true);
				/**
				 * 制造厂商
				 */
				ListGridField field4 = new ListGridField(
						"m_ManufacturerCode.code");
				field4.setCanFilter(true);
				/**
				 * 数量
				 */
				ListGridField field5 = new ListGridField("quantity");
				Utils.formatQuantityField(field5, "m_UnitOfMeasureCode");
				field5.setCanFilter(true);
				/**
				 * 单价
				 */
				ListGridField field6 = new ListGridField("unitPriceAmount");
				Utils.formatPriceField(field6, "currency");
				field6.setCanFilter(true);
				/**
				 * 金额
				 */
				ListGridField field7 = new ListGridField("price");
				Utils.formatPriceField(field7, "currency");
				field7.setCanFilter(true);
				/**
				 * 时寿代码
				 */
				ListGridField field11 = new ListGridField("m_ShelfLifeCode");
				field11.setCanFilter(true);
				setFields(field2, field3, field4, field5, field6,
						field7, field11);
			}
		};
		grid.setHeight(180);
		grid.setCanDragResize(true);
		return grid;
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		
		//新建回购申请单
		this.setTitle(ui.getM().addFormTitle(ui.getM().mod_buyBackSheet_name()));
		
		VLayout container = new VLayout();
		container.setWidth100();
		container.setAlign(Alignment.CENTER);
		itemGrid = getSheetItemGrid();
		TabSet tabSet = new TabSet();
		//回购申请单"
		Tab baseTab = new Tab(ui.getM().mod_buyBackSheet_name());
		baseTab.setPane(new BuyBackSheetAddForm(itemGrid));
		tabSet.addTab(baseTab);
		
		//"附件信息"
		Tab attachmentTab = new Tab(ui.getM().attachment_title());
		attachmentTab.setPane(new AttachmentAddForm());
		tabSet.addTab(attachmentTab);
		tabSet.setHeight(300);
		container.addMember(tabSet);
		// 回购申请单明细表格
		itemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				backSheetItemForm.form.editSelectedData(itemGrid);
				backSheetItemForm.item_save.setTitle(ui.getM().btnModify());
			}
		});
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemGrid.setDataSource(dataSource);
						itemGrid.drawGrid();
						model.sheetItemGrid = itemGrid;
					}
				});
		container.addMember(itemGrid);
		// 回购申请单明细表单
		backSheetItemForm = new BuyBackSheetItemAddForm(ui.getM().mod_buyBackSheet_item_name());
		container.addMember(backSheetItemForm);
		return container;
	}
}
