package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui.view;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class BuyBackSheetViewForm extends VLayout {
	public I18n ui = new I18n();

	public LayoutDynamicForm form;
	public SheetModelLocator model = SheetModelLocator.getInstance();

	/** 业务编号 */
	public TextItem item_no;
	/** 客户名称 */
	public SelectItem item_name;
	/**
	 * 联系人
	 */
	public TextItem item_linkMan;
	/**
	 * 联系方式
	 */
	public TextItem item_linkType;
	/**
	 * 合同依据描述
	 */
	public TextAreaItem item_desc;
	/**
	 * 总金额
	 */
	public TextItem item_totalPrice;
	/**
	 * 总项数
	 */
	public TextItem item_totalCount;
	/**
	 * 备注
	 */
	public TextAreaItem item_remark;
	private BaseListGrid itemGrid;
	public BuyBackSheetViewForm(BaseListGrid itemGrid) {
		this.setHeight100();
		this.setWidth100();
		this.setLayoutLeftMargin(10);
		this.itemGrid=itemGrid;
		/**
		 * 初始化表单
		 */
		form = new LayoutDynamicForm();
		form.setNumCols(6);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						form.editSelectedData(model.backSheetGrid);
						build();
						Utils.setReadOnlyForm(form);
					}
				});
	}

	private void build() {
		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remark"/* , "备注" */);
		item_remark.setRowSpan(2);
		item_remark.setHeight("100%");
		item_remark.setTitleVAlign(VerticalAlignment.TOP);
		/**
		 * 初始化合同依据描述输入域
		 */
		item_desc = new TextAreaItem("contractBasisDesc"/* , "合同依据描述" */);
		item_desc.setHeight(60);
		item_desc.setColSpan(3);
		item_desc.setTitleVAlign(VerticalAlignment.TOP);
		/**
		 * 初始化联系方式输入框
		 */
		item_linkType = new TextItem("contactWay"/* , "联系方式" */);
		item_linkType.setColSpan(3);
		/**
		 * 初始化联系人输入框
		 */
		item_linkMan = new TextItem("linkman"/* , "联系人" */);
		/**
		 * 初始化客户名称输入框
		 */
		item_name = new SelectItem();
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item_name);
		item_name.setName("m_CustomerIdentificationCode.id");
		item_name.setPickListWidth(300);
		item_name.setTitle(ui.getM().mod_customerInfo_btn());
		item_name.setRequired(true);
		item_name.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String id = item_name.getValueAsString();
				CodeRPCTool.bindCustomerData(id, item_linkMan, item_linkType);
			}
		});
		/**
		 * 初始化申请单编号输入框
		 */
		item_no = new TextItem();
		item_no.setName("requisitionSheetNumber");
		item_no.setTitle(ui.getM().title_requisitionSheetNumber());
		item_no.setValue(ui.getB().msgAutoIdInfo());
		item_no.setAttribute("readOnly", true);
		item_no.setTextBoxStyle("back");
		item_no.setDisabled(true);
		item_totalCount = new TextItem();
		item_totalCount.setName("totalCount");
		item_totalCount.setDisabled(true);

		item_totalPrice = new TextItem();
		item_totalPrice.setName("totalAmount");
		item_totalPrice.setDisabled(true);
		form.setItems(item_no, item_name, item_linkMan, item_linkType,
				item_remark, item_desc,item_totalCount, item_totalPrice);

		addMember(form);
		Timer timer = Utils.startAmountTimer(itemGrid, item_totalCount,
				item_totalPrice, "price");
		timer.scheduleRepeating(1000);
	}
}
