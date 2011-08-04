package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.DicKey;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.tabs.SuppliersParityTabSet;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.DateItemSelectorFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;

/**
 * 供应商比价
 * 
 * @author Tony FANG
 * 
 */
public class SuppliersParityWindow extends BaseWindow {

	private static I18n ui = new I18n();
	private ListGrid[] listGrids = null;

	private LayoutDynamicForm ldf;// 主订单From

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private HLayout btnsLayout;// 主订单操作按钮容器

	private FormItem item1;
	private FormItem item2;
	private FormItem item3;
	private FormItem item4;
	private FormItem item5;
	private FormItem item6;
	private FormItem item7;
	private FormItem item8;
	private FormItem item9;
	private FormItem item10;
	private FormItem item11;

	private SelectItem partNumberItem;

	private SuppliersParityModel suppliersParityModel;
	private FilterListGrid quotationItemLG;
	private String partNumber;// 当前明细件号

	// private

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SuppliersParityWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		suppliersParityModel = SuppliersParityModel.getInstance();
		//供应商比价
		this.setTitle(ui.getM().suppliersParity_name());
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		/** 面板1 **/
		VLayout firstView = getSuppliersParityView();
		firstView.setHeight(150);
		firstView.setLayoutTopMargin(5);
		vmain.addMember(firstView);

		/** 面板2 **/
		HLayout secondView = getShowGridView();
		secondView.setHeight(150);
		secondView.setLayoutTopMargin(10);
		vmain.addMember(secondView);
		//
		// /**面板3**/
		SuppliersParityTabSet tabSet = new SuppliersParityTabSet();
		tabSet.setHeight(310);
		vmain.addMember(tabSet);
		//
		// /**面板4（操作按钮）**/
		HLayout fourthView = operationButsView();
		fourthView.setLayoutAlign(VerticalAlignment.CENTER);
		fourthView.setHeight(50);
		vmain.addMember(fourthView);

		return vmain;
	}

	public void disableBtn() {
		this.btnsLayout.disable();
	}

	// 布局一
	private VLayout getSuppliersParityView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		//采购询价单明细列表
		v.setGroupTitle(ui.getM().msgListTitle(ui.getM().mod_procurementInquiry_item_name()));
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(4);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		ListGridRecord selecLGR = suppliersParityModel.procurementInquiryLGR;
		partNumber = selecLGR.getAttribute("partNumber");

		//件号
		partNumberItem = Utils.getPartNumberList();
		partNumberItem.setName("partNumber");
		partNumberItem.setAttribute("readOnly", true);
		partNumberItem.setValue(selecLGR.getAttribute("partNumber"));

		//供应商
		SelectItem item_2 = new SelectItem();
		item_2.setName("procurementInquirySheet.m_supplier");
		item_2.setMultiple(true);
		CodeRPCTool.bindData(CodeRPCTool.SUPPLIERCODE, item_2);
		item_2.setValue(selecLGR.getAttribute("procurementInquirySheet.m_supplier"));
	//	String supplier=selecLGR.getAttribute("procurementInquirySheet.m_supplier");
		//SC.say("supplier"+supplier);
//		if(supplier!=null&&"".equals(supplier)){
//			String[] supplierArray= supplier.split(",");
//			if(supplierArray==null)
//			item_2.setValues(supplierArray);
//		}
	
		
		//关键字
		TextItem item_3 = new TextItem();
		item_3.setDisabled(true);

		//证书类型
		SelectItem item_4 = new SelectItem();
		item_4.setName("m_CertificateType");
		item_4.setDisabled(true);
		item_4.setValue(selecLGR.getAttribute("m_CertificateType"));
		
		//需求日期
		DateItem item_5 = new DateItem();
		item_5.setName("partRequireDate");
		item_5.setDisabled(true);
		item_5.setValue(selecLGR.getAttribute("partRequireDate"));
		item_5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item_5.setUseTextField(true);

		//备注
		TextAreaItem item_6 = new TextAreaItem();
		item_6.setName("remark");
		item_6.setTitleVAlign(VerticalAlignment.TOP);
		item_6.setRowSpan(3);
		item_6.setHeight("100%");
		item_6.setDisabled(true);
		item_6.setValue(selecLGR.getAttribute("remark"));

		//采购数量
		TextItem item_7 = new TextItem();
		item_7.setName("quantity");
		item_7.setDisabled(true);
		item_7.setValue(selecLGR.getAttribute("quantity"));

		//指定交货地点
		TextItem item_8 = new TextItem();
		item_8.setName("deliveryAddress");
		item_8.setDisabled(true);
		item_8.setValue(selecLGR.getAttribute("deliveryAddress"));

		//计划采购单价
		TextItem item_9 = new TextItem();
		item_9.setName("planUnitPrice");
		item_9.setDisabled(true);
		item_9.setDefaultValue(0.0f);
		item_9.setValue(selecLGR.getAttribute("planUnitPrice"));

		//计划采购金额
		TextItem item_10 = new TextItem();
		item_10.setName("planAmount");
		item_10.setDisabled(true);
		item_10.setDefaultValue(0.0f);
		item_10.setValue(selecLGR.getAttribute("planAmount"));

		ldf.setFields(partNumberItem, item_2, item_3, item_4, item_5, item_6,
				item_7, item_8, item_9, item_10);

		v.setMembers(ldf);

		return v;
	}

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		//供应商比价
		Label leftLb = new Label(ui.getM().suppliersParity_name());
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		h.addMember(leftLayout);
		return h;
	}

	// 供应商比价
	private ListGrid getLeftGrid() {
		quotationItemLG = new FilterListGrid();
		quotationItemLG.setAlign(Alignment.CENTER);
		Utils.setListGridHeight(quotationItemLG);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化报价单明细据源
		dataSourceTool.onCreateDataSource(
				DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM,
				DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						quotationItemLG.setDataSource(dataSource);
						suppliersParityModel.procurementQuotationLG=quotationItemLG;
						Criteria criteria = new Criteria();
						criteria.setAttribute("quotationPartNumber", partNumber);
						criteria.setAttribute("_r", String
								.valueOf(Math.random()));
						quotationItemLG.fetchData();
						
						//供应商代码(CAGE码)
						ListGridField field1 = new ListGridField(
								"procurementQuotationSheetRecord.supplierCode.code");

						//优先级
						ListGridField field3 = new ListGridField("m_Priority"
								);

						//报价件号
						ListGridField field4 = new ListGridField(
								"quotationPartNumber");

						// ListGridField field5 = new ListGridField("field5",
						// "互换性");

						//报价
						ListGridField field6 = new ListGridField("amount");

						//币种
						ListGridField field7 = new ListGridField(
								"m_InternationalCurrencyCode", "币种");

						//交货期(天)
						ListGridField field8 = new ListGridField(
								"deliveryLeadTime");

						//报价日期
						ListGridField field12 = new ListGridField("createDate");
						field12
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

						//证书类型
						ListGridField field9 = new ListGridField(
								"m_CertificateType");

						//付款要求
						ListGridField field10 = new ListGridField(
								"paymentRequirement");

						//加入比价单
						ListGridField field11 = new ListGridField(
								"isJoinParity");
						field11.setCellFormatter(new CellFormatter() {
							public String format(Object value,
									ListGridRecord record, int rowNum,
									int colNum) {
								if(value==null){
									return ui.getM().title_no();
								}
								if(value.equals(true)){
									return "<font color='green'>"+ui.getM().title_no()+"</font>";
								}else{
									return ui.getM().title_no();
								}
							}
						});

						field1.setCanFilter(true);
						field3.setCanFilter(true);
						field4.setCanFilter(true);
						field6.setCanFilter(true);
						field7.setCanFilter(true);
						field8.setCanFilter(true);
						field9.setCanFilter(true);
						field10.setCanFilter(true);
						field11.setCanFilter(true);
						field12.setCanFilter(true);
						quotationItemLG.setFields(field1,  field3, field4,
								field6, field7, field8, field12, field9,
								field10, field11);
					}
				});

		quotationItemLG.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				if(suppliersParityModel.procurementQuotationForm!=null){
					suppliersParityModel.procurementQuotationForm.editSelectedData(quotationItemLG);
					refreshItemListGrid();
				}
			
				
			}

		});

		return quotationItemLG;
	}

	/**
	 * 操作按钮容器
	 * 
	 * @return
	 */
	private HLayout operationButsView() {
		HLayout h = new HLayout();

		h.setMembersMargin(8);
		h.setWidth100();
		h.setHeight100();
		h.setLayoutMargin(15);
		IButton addBtn = new IButton();
		//加入比价单
		addBtn.setTitle(ui.getM().suppliersParity_add());
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ListGridRecord lgr =quotationItemLG.getSelectedRecord();
				if(lgr.getAttributeAsBoolean("isJoinParity")==true){
					//已加入比价单
					SC.say(ui.getM().suppliersParity_already_add());
					return;
				}
				lgr.setAttribute("isJoinParity", true);
				quotationItemLG.updateData(lgr);
			}
		});

		IButton revokedBtn = new IButton();
		//撤销比价单
		revokedBtn.setTitle(ui.getM().suppliersParity_repeal());
		revokedBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ListGridRecord lgr =quotationItemLG.getSelectedRecord();
				
				lgr.setAttribute("isJoinParity", false);
				quotationItemLG.updateData(lgr);
				
			}
		});

		IButton closeBtn = new IButton();
		//关闭
		closeBtn.setTitle(ui.getM().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				destroy();
			}

		});

		return h;
	}
	
	private void refreshItemListGrid(){
		Criteria criteria = new Criteria();
		criteria.addCriteria("quotationPartNumber", partNumber);
		criteria.addCriteria("_r", String
				.valueOf(Math.random()));
		quotationItemLG.fetchData();
	}

}
