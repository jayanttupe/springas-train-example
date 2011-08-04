package com.skynet.spms.client.gui.customerplatform.guaranteeForm.update;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerplatform.guaranteeForm.model.GuaranteeModelLocator;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 添加担保索赔申请
 * 
 * @author fl
 * 
 */
public class GuaranteeUpdateWin extends BaseWindow {

	public GuaranteeUpdateWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public GuaranteeUpdateWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			OperationMode opm) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		initViewState(opm);
	}

	/**
	 * 根据操作方式 初始化试图
	 * 
	 * @param opm
	 */
	private void initViewState(OperationMode opm) {
		/** 根据当前操作方式，设置页面 **/
		if (opm != null) {
			// 如果是添加主订单，则保存明细按钮 禁用
			if (opm.equals(OperationMode.add)) {
			} else if (opm.equals(OperationMode.addItem)) {
			}
		}
	}
	private GuaranteeModelLocator model;
	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model=GuaranteeModelLocator.getInstance();
		VLayout vLayout = new VLayout();
		vLayout.setMembersMargin(15);
		vLayout.setHeight100();

		VLayout tabsLayout = new VLayout();
		tabsLayout.setHeight(230);
		vLayout.addMember(tabsLayout);

		final TabSet topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);
		topTabSet.setWidth100();
		tabsLayout.addMember(topTabSet);

		// 担保索赔申请单内容
		Tab tab_sheet = new Tab("担保索赔申请单");
		GuaranteeSheetForm topVlayout = new GuaranteeSheetForm();
		tab_sheet.setPane(topVlayout);
		topTabSet.addTab(tab_sheet);
		// 担保索赔申请单附件
		Tab tab_affix = new Tab("附件");
		tab_affix.setPane(new AttachmentUpdateForm());
		topTabSet.addTab(tab_affix);

		VLayout leftLayout = new VLayout();
		Label leftLb = new Label("担保索赔申请单明细");
		leftLayout.setMembersMargin(4);
		leftLb.setHeight("15");
		leftLayout.addMember(leftLb);// 先放label
		final BaseListGrid itemGrid = getSheetItemGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_GUARANTEESHEET,
				DSKey.C_GUARANTEESHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("sheet.id",model.sheetID);
						itemGrid.fetchData(criteria);
						itemGrid.drawGrid();
					}
				});
		leftLayout.addMember(itemGrid);// 再放Grid
		vLayout.addMember(leftLayout);
		final GuaranteeSheetItemForm itemForm = new GuaranteeSheetItemForm(
				"担保索赔申请单明细项", itemGrid);
		vLayout.addMember(itemForm);
		itemGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				itemForm.form.reset();
				itemForm.form.editSelectedData(itemGrid);
				itemForm.item_save.setTitle("修改");
			}
		});
		return vLayout;
	}

	/**
	 * 担保申请单明细项表格
	 * 
	 * @return
	 */
	public BaseListGrid getSheetItemGrid() {
		BaseListGrid lg = new BaseListGrid() {

			@Override
			public void drawGrid() {
				setLoadingDataMessage("数据加载中，请稍后......");
				setHeight(120);
				ListGridField field2 = new ListGridField("partNumber"/*, "件号"*/);
				field2.setCanFilter(true);

//				ListGridField field3 = new ListGridField("keyword"/*, "关键字"*/);
//				field3.setCanFilter(true);

				ListGridField field4 = new ListGridField(
						"m_ManufacturerCode.code"/*, "制造厂商"*/);

				ListGridField field5 = new ListGridField("quantity"/*, "数量"*/);
				Utils.formatQuantityField(field5);
				field5.setCanFilter(true);

				ListGridField field6 = new ListGridField("remarkText"/*, "备注"*/);
				field6.setCanFilter(true);

				setFields(field2,/* field3,*/ field4, field5, field6);

				addSelectionChangedHandler(new SelectionChangedHandler() {
					public void onSelectionChanged(SelectionEvent event) {
					}
				});
			}
		};
		return lg;
	}
}
