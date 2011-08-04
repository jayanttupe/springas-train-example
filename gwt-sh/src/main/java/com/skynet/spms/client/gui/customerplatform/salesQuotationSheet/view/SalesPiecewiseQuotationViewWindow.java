package com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.CommonWindow;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorEnterEvent;
import com.smartgwt.client.widgets.grid.events.EditorEnterHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 分段报价
 * 
 * @author Tony FANG
 * 
 */
public class SalesPiecewiseQuotationViewWindow extends CommonWindow {

	private static I18n ui = new I18n();
	
	public HLayout btnsView;
	
	private String quotationItemId;//报价单id
	
	// 分段报价Grid
	private FilterListGrid listGrid;
	
	
	public String fromPrimaryKey="";

	public SalesPiecewiseQuotationViewWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	
	
	
	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		this.setTitle(ui.getM().mod_salesPiecewiseQuotation_name());
		
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(10);

		/** 操作按钮* */
		HLayout oneLayout = new HLayout();
		oneLayout.setHeight(20);
		oneLayout.setLayoutMargin(5);

		/** 分段报价Grid* */
		HLayout twoLayout = new HLayout();
		twoLayout.setLayoutMargin(5);
		twoLayout.addMember(buildListGrid());

		vmain.setMembers(oneLayout, twoLayout);

		return vmain;
	}


	/**
	 * 构建分段报价Grid
	 */
	private HLayout buildListGrid() {
		HLayout view = new HLayout();

		listGrid = new FilterListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record,
					Integer colNum) {

				String fieldName = this.getFieldName(colNum);
				// 操作
				if (fieldName.equals("BZ")) {
					final LayoutDynamicForm form = new LayoutDynamicForm();
					form.setCellPadding(0);
					form.setAlign(Alignment.CENTER);
					form.setDataSource(listGrid.getDataSource());

					DicSelectItem item1 = new DicSelectItem();
					item1.setName("m_InternationalCurrencyCode");
					item1.setShowTitle(false);
					form.setFields(item1);
					return form;
				}
				return null;
			}
		};
		listGrid.setShowFilterEditor(false);

		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(
				DSKey.C_SALESPIECEWISEQUOTATION_ITEM,
				DSKey.C_SALESPIECEWISEQUOTATION_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						
						refreshItemListGrid();//刷新数据源

						//起始数量
						ListGridField field1 = new ListGridField(
								"fromQuantity");
						SpinnerItem fromItem = new SpinnerItem("fromQuantity");
						fromItem.setShowTitle(false);
						field1.setEditorType(fromItem);
						field1.setRequired(true);
						
						//截止数量
						ListGridField field2 = new ListGridField("toQuantity");
						SpinnerItem toItem = new SpinnerItem("toQuantity");
						toItem.setShowTitle(false);
						field2.setEditorType(toItem);
						field2.setRequired(true);

						//分段报价
						ListGridField field3 = new ListGridField("priceAmount");
						SpinnerItem priceItem = new SpinnerItem("priceAmount");
						priceItem.setShowTitle(false);
						field3.setEditorType(priceItem);
						field3.setRequired(true);
					
						//m_InternationalCurrencyCode
						ListGridField field4 = new ListGridField("m_InternationalCurrencyCode"/*, "币种"*/);
						DicSelectItem item1 = new DicSelectItem();
						item1.setName("m_InternationalCurrencyCode");
						item1.setShowTitle(false);
						field4.setEditorType(item1);
						field4.setRequired(true);

						//销售报价明细Id
						ListGridField  field5= new ListGridField("salesQuotationSheetItem.id");
						field5.setHidden(true);
						
						//分段报价类型
						ListGridField  field7= new ListGridField("quantityType");
						field7.setHidden(true);
						
						
						listGrid.addEditorEnterHandler(new EditorEnterHandler(){

							public void onEditorEnter(EditorEnterEvent event) {
								listGrid.setEditValue(event.getRowNum(), "salesQuotationSheetItem.id", quotationItemId);
								listGrid.setEditValue(event.getRowNum(), "quantityType", "sales");
							}
							
						});
						
						listGrid.setFields(field1, field2, field3, field4,field5,field7);
					}
				});

		view.addMember(listGrid);
		return view;
	}
	
	/**
	 * 刷新listGrid
	 */
	private void refreshItemListGrid() {
		if (quotationItemId == null) {
			quotationItemId = "-1";
		}
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", quotationItemId);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		listGrid.fetchData(criteria);
	}

	public String getQuotationItemId() {
		return quotationItemId;
	}

	public void setQuotationItemId(String quotationItemId) {
		refreshItemListGrid();
		this.quotationItemId = quotationItemId;
	}



}
