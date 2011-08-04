package com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.business.SalesRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SalesRequisitionTrackView extends BaseWindow {
	
	private static I18n ui = new I18n();
	
	private SalesRequisitionModel modelLocator;

	private DynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private IButton saveItemBtn;// 明细操作按钮

	private FormItem totalAmountItem;

	private FormItem item1;
	private DateItem item2;
	private SelectItem item3;
	private SelectItem item4;
	private FormItem item6;
	private FormItem item7;
	private SelectItem item8;
	private TextItem item9;
	private FormItem item10;
	private DicSelectItem item11;
	private SelectItem item12;
	private TextItem item13;
	private TextAreaItem item14;
	private FormItem item15;
	private FormItem item16;
	private FormItem keywordItem;//关键字
	private FormItem ataItem;//ATA章节号
	private SelectItem manufacturerItem;//制造商
	private FormItem partDescriptionItem;//件号描述
	private SelectItem certificateTypeItem;//证书类型

	private FormItem itemNumber;
	private String primaryKey;// 主表id


	
	
	private ListGrid requisitionLG;
	private ListGrid requisitionItemLG;
	private static BaseCodeServiceAsync service = GWT
	.create(BaseCodeService.class);

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SalesRequisitionTrackView(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		this.setTitle(ui.getM().viewFormTitle(ui.getM().mod_salesRequisitionTrack_view()));
		modelLocator = SalesRequisitionModel.getInstance();
		primaryKey = modelLocator.primaryKey;
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		// /**面板1**/
		VLayout oneView = getSearchView();
		oneView.setHeight("10%");
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 * */
		VLayout twoView = getShowGridView(); 
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);


		return vmain;
	}

	public void disableBtn() {
		this.btnsLayout.disable();
	}

	// 布局一
	private VLayout getSearchView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_salesRequisitionSheet_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		HLayout searchBar = new HLayout(10);
		searchBar.setLayoutAlign(Alignment.CENTER);
		searchBar.setLayoutMargin(10);
		ldf = new DynamicForm();
		ldf.setWidth(360);
		ldf.setColWidths(160, 200);
		// 搜索编号
		final TextItem numberItem = new TextItem();
		numberItem.setName("requisitionSheetNumber");
		numberItem.setValue(modelLocator.trackListGrid.getSelectedRecord().getAttribute("requisitionSheetNumber"));
		numberItem.setTitle("CPO");
		numberItem.setWidth(200);
		numberItem.setTitleAlign(Alignment.RIGHT);
		ldf.setFields(numberItem);
		
		//搜索按钮
		IButton searchBtn = new IButton();
		searchBtn.setTitle(ui.getM().mod_salesRequisitionTrack_btnSearch());
		searchBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Criteria criteria = new Criteria();
				criteria.setAttribute("requisitionSheetNumber",numberItem.getValueAsString());
				criteria.setAttribute("_r", String.valueOf(Math.random()));
				requisitionLG.fetchData(criteria);
				
				
				Criteria requisitionItemCriteria = new Criteria();
				requisitionItemCriteria.setAttribute("_r", String.valueOf(Math.random()));
				requisitionItemCriteria.setAttribute("requisitionSheetNumber",numberItem.getValueAsString());
				requisitionItemLG.fetchData(requisitionItemCriteria);
				
			}
		});
		
		searchBar.setMembers(ldf,searchBtn);
		v.setMembers(searchBar);
		return v;
	}

	// 布局2
	private VLayout getShowGridView() {
		VLayout v = new VLayout();
		v.setBorder("1px solid #a6abb4");
		v.setWidth100();
		v.setHeight100();
		v.setMembersMargin(5);
		v.setLayoutTopMargin(3);

		/**显示订单基本信息**/
		//订单信息
		v.addMember(getShowView(ui.getM().mod_salesRequisitionTrack_view_lg_first(),buildSalesRequisitionLG()));
		//订单明细
		v.addMember(getShowView(ui.getM().mod_salesRequisitionTrack_view_lg_second(),buildSalesRequisitionItemLG()));
		//物流信息
		v.addMember(getShowView(ui.getM().mod_salesRequisitionTrack_view_lg_three(),new ListGrid()));
		//装箱信息
		v.addMember(getShowView(ui.getM().mod_salesRequisitionTrack_view_lg_four(),new ListGrid()));
		//发票信息
		v.addMember(getShowView(ui.getM().mod_salesRequisitionTrack_view_lg_five(),new ListGrid()));
		
		return v;
	}
	
	/**
	 * 
	 * 构建订单信息
	 *
	 * @param  @return   
	 * @return ListGrid  
	 * @throws
	 */
	private ListGrid buildSalesRequisitionLG(){
		requisitionLG = new ListGrid();
		requisitionLG.setDataSource(modelLocator.trackDataSource);
		//CPO:订单编号
		ListGridField field1 = new ListGridField("requisitionSheetNumber","CPO");
		//ORD：订单创建时间
		ListGridField field2 = new ListGridField("createDate","ORD");
		field2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		//SSD：特别物流时间
		ListGridField field3 = new ListGridField("","SSD");
		
		ListGridRecord selLgr=modelLocator.trackListGrid.getSelectedRecord();
		
		ListGridRecord[] lgrs= new ListGridRecord[]{selLgr} ;
		requisitionLG.setData(lgrs);
		
		requisitionLG.setFields(field1,field2,field3);
		
		
		return requisitionLG;
	}
	/**
	 * 
	 * 构建订单明细信息
	 *
	 * @param  @return   
	 * @return ListGrid  
	 * @throws
	 */
	private ListGrid buildSalesRequisitionItemLG(){
		requisitionItemLG = new ListGrid();
		requisitionItemLG.setDataSource(modelLocator.trackItemDataSource);
		//PNR：件号
		ListGridField field1 = new ListGridField("partNumber","PNR");
		//MFR：制造商代码
		ListGridField field2 = new ListGridField("m_ManufacturerCode.code","MFR");
		//QTY：数量
		ListGridField field3 = new ListGridField("quantity","QTY");
		//UNT：单位
		ListGridField field4 = new ListGridField("m_UnitOfMeasureCode","UNT");
		//UNP：单价
		ListGridField field5 = new ListGridField("unitOfPrice","UNP");
		//KWD：关键字
		ListGridField field6 = new ListGridField("keyword","KWD");
		//SSD：特别物流时间
		ListGridField field7 = new ListGridField("","SSD");
		//SCD：预计物流时间
		ListGridField field8 = new ListGridField("","SCD");
		
		ListGridRecord[] lgrs=modelLocator.trackItemListGrid.getRecords();
		requisitionItemLG.setData(lgrs);
		
		requisitionItemLG.setFields(field1,field2,field3,field4,field5,field6,field7,field8);
		return requisitionItemLG;
	}
	private VLayout getShowView(String titleName,ListGrid listGrid){
		VLayout v = new VLayout();
		v.setBorder("1px solid #a6abb4");
		v.setLayoutMargin(5);
		v.setWidth100();
		v.setHeight100();
		HLayout hTitle = new HLayout();
		hTitle.setHeight("20");
		hTitle.setLayoutAlign(Alignment.CENTER);
		hTitle.setAlign(Alignment.CENTER);
		Label rightLb = new Label(titleName);
		hTitle.addMember(rightLb);
		v.addMember(hTitle);
		v.addMember(listGrid);
		return v;
	}




}
