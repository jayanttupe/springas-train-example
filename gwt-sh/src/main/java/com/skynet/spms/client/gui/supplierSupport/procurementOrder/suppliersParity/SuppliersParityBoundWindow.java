package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
public class SuppliersParityBoundWindow extends BaseWindow {
	public HLayout btnsView;
	private I18n ui = new I18n();
	// 已比价的报价明细ID
	private String suppliersParityItemIds;
	
	//已比价的供应商code
	private String suppliersNames;

	//可比价ListGrid
	private FilterListGrid itemListGrid;
	
	//原来已经绑定的比较单
	private FilterListGrid oldSuppliersParityLG;

	private SuppliersParityModel model;
	
	//比价单idsItem
	public FormItem suppliersParityItem;
	//比价单供应商
	public FormItem supplierNameItem;
	
	//原绑定比价单的List
	private List<ListGridRecord> oldListRecord = new ArrayList<ListGridRecord>();
	
	public SuppliersParityBoundWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);

	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model=SuppliersParityModel.getInstance();
		suppliersParityItemIds= model.suppliersParityItemIds;
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(10);

		/** 带绑定比价单* */
		VLayout twoLayout = new VLayout();
		twoLayout.setLayoutMargin(5);
		//待绑定比价单列表"
		Label twoLb = new Label(ui.getM().suppliersParity_wait_bond());
		twoLb.setHeight("20");
		twoLayout.addMember(twoLb);// 先放label
		twoLayout.addMember(buildItemListGrid());
		
		/** 操作按钮* */
		HLayout btnLayout = new HLayout();
		btnLayout.setHeight(20);
		btnLayout.setLayoutMargin(5);
		btnLayout.addMember(buildBtns());
		

		/** 已绑定比价单 */
		VLayout threeLayout = new VLayout();
		//"比价单列表"
		Label threeLb = new Label(ui.getM().suppliersParity_list());
		threeLb.setHeight("20");
		threeLayout.addMember(threeLb);// 先放label
		threeLayout.addMember(buildOldSuppliersPairtyLG());// 再放Grid
		
		/** 底层操作按钮* */
		HLayout lastBtnLayout = new HLayout();
		lastBtnLayout.setHeight(20);
		lastBtnLayout.setLayoutMargin(5);
		
		lastBtnLayout.addMember(buildLastBtns());
		
		
		vmain.setMembers( twoLayout, btnLayout,threeLayout,lastBtnLayout);

		return vmain;
	}

	/**
	 * 构建操作按钮
	 */
	private HLayout buildBtns() {
		HLayout btnView = new HLayout();
		btnView.setMembersMargin(5);
		btnView.setHeight(20);
		
		//"确认绑定"
		IButton saveBtn = new IButton(ui.getM().suppliersParity_submit_bond());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateSuppliersParityItemIds();
			}
		});
		
		//"取消"
		IButton cancelBtn = new IButton(ui.getM().btnCancel());
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemListGrid.selectRecords(itemListGrid.getRecords(), false);
			}
		});
		
		//"关闭"
		IButton closeBtn = new IButton(ui.getM().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();

			}
		});
		btnView.setMembers( saveBtn, cancelBtn,closeBtn);
		return btnView;
	}
	
	private HLayout buildLastBtns() {
		HLayout btnView = new HLayout();
		btnView.setMembersMargin(5);
		btnView.setHeight(20);
		
		//删除绑定
		IButton saveBtn = new IButton(ui.getM().suppliersParity_del_bond());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(oldSuppliersParityLG.getSelectedRecord()!=null){
					//待删除记录
					ListGridRecord[] selRecords = oldSuppliersParityLG.getSelection();
					for (ListGridRecord selRecord : selRecords) {
						
						for (ListGridRecord listGridRecord : oldListRecord) {
							if(selRecord.getAttribute("id").equals(listGridRecord.getAttribute("id"))){
								oldListRecord.remove(listGridRecord);
							}
						}
					}
					//重新设置比价单ids
					afreshSetSupliersParityIds();
				}
				
				
			}
		});
		btnView.addMember(saveBtn);
		return btnView;
	}

	/**
	 * 构建待绑定比价Grid
	 */
	private HLayout buildItemListGrid() {
		HLayout view = new HLayout();

		itemListGrid = new FilterListGrid();
		itemListGrid.setCanEdit(false);
		itemListGrid.setShowFilterEditor(true);
		itemListGrid.setSelectionType(SelectionStyle.SIMPLE);  
		itemListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
		

		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM,
				DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemListGrid.setDataSource(dataSource);
						
						model.procurementQuotationItemDS=dataSource;
						
						refreshItemListGrid();// 刷新数据源
						
						//"供应商代码(CAGE码)"
						ListGridField field1 = new ListGridField(
								"procurementQuotationSheetRecord.supplierCode.code"
								);

//						ListGridField field2 = new ListGridField(
//								"procurementQuotationSheetRecord.supplierCode.code",
//								"供应商名称");
						//"优先级"
						ListGridField field3 = new ListGridField("m_Priority"
								);

						//, "报价件号"
						ListGridField field4 = new ListGridField(
								"quotationPartNumber", "报价件号");

						
						//报价
						ListGridField field6 = new ListGridField("amount");

						//币种
						ListGridField field7 = new ListGridField(
								"m_InternationalCurrencyCode");
						
						//交货期(天)
						ListGridField field8 = new ListGridField(
								"deliveryLeadTime");

							//报价日期
						ListGridField field12 = new ListGridField("createDate"
								);
						field12
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

//
						field1.setCanFilter(true);
						//field2.setCanFilter(true);
						field3.setCanFilter(true);
						field4.setCanFilter(true);
						field6.setCanFilter(true);
						field7.setCanFilter(true);
						field8.setCanFilter(true);
						field12.setCanFilter(true);
						itemListGrid.setFields(field1, field3, field4,
								field6, field7, field8, field12);
						
						
						
					
					}
				});

		view.addMember(itemListGrid);
		return view;
	}
	
	/***
	 * 原绑定的比价单
	 * @return
	 */
	private  HLayout buildOldSuppliersPairtyLG(){
		HLayout view = new HLayout();

		oldSuppliersParityLG = new FilterListGrid();
		oldSuppliersParityLG.setCanEdit(false);
		oldSuppliersParityLG.setShowFilterEditor(true);
		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM,
				DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						oldSuppliersParityLG.setDataSource(dataSource);
						
						model.procurementQuotationItemDS=dataSource;

						refreshOldSuppliersPairtyLG();// 刷新数据源
					
						//"供应商代码(CAGE码)"
						ListGridField field1 = new ListGridField(
								"procurementQuotationSheetRecord.supplierCode.code"
								);

//						ListGridField field2 = new ListGridField(
//								"procurementQuotationSheetRecord.supplierCode.code",
//								"供应商名称");
						
						//"优先级"
						ListGridField field3 = new ListGridField("m_Priority"
								);

						//
						ListGridField field4 = new ListGridField(
								"quotationPartNumber");

						//报价
						ListGridField field6 = new ListGridField("amount");

						//币种
						ListGridField field7 = new ListGridField(
								"m_InternationalCurrencyCode");

						//"交货期(天)"
						ListGridField field8 = new ListGridField(
								"deliveryLeadTime");

						//报价日期
						ListGridField field12 = new ListGridField("createDate");
						field12
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

						field1.setCanFilter(true);
//						field2.setCanFilter(true);
						field3.setCanFilter(true);
						field4.setCanFilter(true);
						field6.setCanFilter(true);
						field7.setCanFilter(true);
						field8.setCanFilter(true);
						field12.setCanFilter(true);
						oldSuppliersParityLG.setFields(field1,  field3, field4,
								field6, field7, field8, field12);
						
						initOldSuppliersParityList();//获取原来的ListGridRecord
					}
				});

		view.addMember(oldSuppliersParityLG);
		return view;
	}
	
	

	/***
	 * 更改绑定的比价id
	 */
	private void updateSuppliersParityItemIds(){
		
		
		//待绑定比价单的选中项
		ListGridRecord[] itemRecords = itemListGrid.getSelection();
		
		//给原值 加入新值
		if(itemRecords!=null){
			initOldSuppliersParityList();
			
			String newItemId="";
			String newSupplierCodeId="";
			String oldItemId="";
			String oldSuppliderCodeId="";
			boolean isAdd=true;
			//待添加比价单
			for (ListGridRecord itemRecord : itemRecords) {
				newItemId=itemRecord.getAttribute("id");
				newSupplierCodeId=itemRecord.getAttribute("procurementQuotationSheetRecord.supplierCode.id");
				
				//遍历己经存在的比价单
				for (ListGridRecord oldRecord : oldListRecord) {
					oldItemId=oldRecord.getAttribute("id");
					oldSuppliderCodeId=oldRecord.getAttribute("procurementQuotationSheetRecord.supplierCode.id");
					
					//如果比价单已经存在，则跳出
					if(newItemId.equals(oldItemId)){
						isAdd=false;
						break;
					}
					//如果该供应商的比价单 已经存在过一个，则替换掉原来的
					if(newSupplierCodeId.equals(oldSuppliderCodeId)){
						//先删除原来的比价单
						oldListRecord.remove(oldRecord);
						break;
					}
				}
				if(isAdd){
					//再添加进去
					oldListRecord.add(itemRecord);
				}
				isAdd=true;
			}
			//重新设置比价单ids
			afreshSetSupliersParityIds();
		}
		
	}

	
	/**
	 * 初始化原比价单记录
	 */
	private void initOldSuppliersParityList (){
		//原绑定比价单的记录
		ListGridRecord[] oldRecords = oldSuppliersParityLG.getRecords();
		oldListRecord = new ArrayList<ListGridRecord>();
		//添加原值进入List
		if(oldRecords!=null){
			for (ListGridRecord oldRecord : oldRecords) {
				oldListRecord.add(oldRecord);
			}
		}
	}
	
	/***
	 * 重新设置比价单ids
	 */
	private void afreshSetSupliersParityIds(){
		suppliersParityItemIds="";
		suppliersNames="";
		
		for (ListGridRecord oldRecord : oldListRecord) {
			suppliersParityItemIds+=oldRecord.getAttribute("id")+",";
			suppliersNames+=oldRecord.getAttribute("procurementQuotationSheetRecord.supplierCode.code")+",";
		}
	
		suppliersParityItemIds=suppliersParityItemIds.substring(0, suppliersParityItemIds.length()-1);
		suppliersNames=suppliersNames.substring(0, suppliersNames.length()-1);
		refreshOldSuppliersPairtyLG();
		suppliersParityItem.setValue(suppliersParityItemIds);
		supplierNameItem.setValue(suppliersNames);
		
	}
	
	/***
	 * 刷新可绑定比价单列表
	 */
	private void refreshItemListGrid() {
		
		Criteria criteria = new Criteria();
		criteria.setAttribute("quotationPartNumber", model.partNumber);
		criteria.setAttribute("isJoinParity", true);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
	}


	/**
	 * 刷新已经绑定比价单列表
	 */
	private void refreshOldSuppliersPairtyLG() {
		if (suppliersParityItemIds == null||"".equals(suppliersParityItemIds)) {
			suppliersParityItemIds = "-1";
		}
		Criteria criteria = new Criteria();
		criteria.setAttribute("suppliersParityItemIds", suppliersParityItemIds);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		oldSuppliersParityLG.fetchData(criteria);
	}
}
