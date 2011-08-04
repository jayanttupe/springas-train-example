package com.skynet.spms.client.gui.customerService.salesService.salesContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContractItemViewForm extends VLayout {
	private I18n ui = new I18n();
	public LayoutDynamicForm form;
	/** 编号 件号 **/
	public SelectItem item_no;
	/**
	 * 件号描述
	 */
	public TextItem item_description;
	/** 制造商 **/
	public SelectItem item_made;
	/** ATA章节号· **/
	public SelectItem item_ata;
	/** 适用机型 */
	public SelectItem item_type;
	/** 关键字 **/
	public TextItem item_keys;
	/** 随件资料 */
	public SelectItem item_docs;
	/** 交货日期 **/
	public DateItem deliveryDate;
	/** 单位下拉列表 */
	public SelectItem item_unit;
	/** 数量 **/
	public SpinnerItem item_count;
	/** 币种 **/
	public SelectItem item_currency;
	/** 单价 */
	public SpinnerItem item_unitPrice;
	/** 备注 */
	public TextAreaItem item_remark;
	/** 金额输入框 */
	public SpinnerItem item_price;
	/**
	 * 折扣
	 */
	public SelectItem item_m_DiscountPercentCode;

	/** 用于保存申请单或其他相关信息的ID */
	public TextItem item4Id;

	public ContractItemViewForm(String groupTitle) {
		this.setHeight(350);
		this.setWidth100();
		this.setOverflow(Overflow.AUTO);
		this.setGroupTitle(groupTitle);
		this.setIsGroup(true);
		this.setLayoutLeftMargin(10);

		form = new LayoutDynamicForm();
		form.setWidth100();
		form.setHeight100();
		form.setNumCols(6);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACTITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						buidItems();
						Utils.setReadOnlyForm(form);
					}
				});
	}

	private void buidItems() {

		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remarkText"/* , "备注" */);
		item_remark.setColSpan(5);
		item_remark.setTitleVAlign(VerticalAlignment.TOP);

		/**
		 * 初始化金额输入框
		 */
		item_price = new SpinnerItem("price"/* , "金额" */);
		/**
		 * 初始化币种下拉列表
		 */
		item_currency = new SelectItem("currency"/* , "币种" */);
		/**
		 * 初始化单价输入框
		 */
		item_unitPrice = new SpinnerItem("unitPrice"/* , "单价" */);
		/**
		 * 初始化单位下拉列表
		 */
		item_unit = new SelectItem("m_UnitOfMeasureCode"/* , "单位" */);
		/**
		 * 初始化数量输入框
		 */
		item_count = new SpinnerItem("quantity"/* , "数量" */);
		/**
		 * 初始化使用机型下拉列表
		 */
		item_type = new SelectItem("m_ModelofApplicabilityCode"/* , "适用机型" */);
		item_type.setMultiple(true);
		/**
		 * 初始化关键字输入框
		 */
		item_keys = new TextItem("keyword"/* , "关键字" */);
		item_keys.setDisabled(true);
		/**
		 * 初始化随件资料下拉列表
		 */
		item_docs = new SelectItem("m_CertificateType"/* , "随件资料" */);
		item_docs.setMultiple(true);

		deliveryDate = new DateItem();
		deliveryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		deliveryDate.setUseTextField(true);
		deliveryDate.setName("deliveryDate");

		/**
		 * 初始化制造商下拉列表
		 */
		item_made = new SelectItem("m_ManufacturerCode.id"/* , "制造商" */);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item_made);
		item_made.setDisabled(true);
		/** ata章节号 */
		item_ata = new SelectItem("ata");
		item_ata.setDisabled(true);
		/**
		 * 初始化件号输入框
		 */
		item_no = Utils.getPartNumberList();
		item_no.setStartRow(true);
		// item_no.setTitle("件号");
		item_no.setName("partNumber");
		item_no.setType("comboBox");
		item_no.setRequired(true);
		item_no.setDisabled(true);
		FormItemIcon btnPartItem = new FormItemIcon();
		btnPartItem.setPrompt(ui.getM().mod_showpartdocument());
		item_no.setIcons(btnPartItem);

		item_description = new TextItem("description");
		/**
		 * 折扣
		 */
		item_m_DiscountPercentCode = new SelectItem("m_DiscountPercentCode");

		item4Id = new TextItem();
		item4Id.setName("salesTemplate.id");
		item4Id.setVisible(false);
		form.setItems(item_no, item_description, item_made, item_ata,
				item_type, item_keys, item_docs, deliveryDate, item_unit,
				item_count, item_currency, item_unitPrice, item_price,
				item_m_DiscountPercentCode, item_remark, item4Id);
		this.addMember(form);
	}
}
