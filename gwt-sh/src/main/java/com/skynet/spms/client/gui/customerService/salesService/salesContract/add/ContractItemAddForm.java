package com.skynet.spms.client.gui.customerService.salesService.salesContract.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContractItemAddForm extends VLayout {
	private I18n ui = new I18n();
	private SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();
	public LayoutDynamicForm form;
	/** 编号 件号 **/
	public SelectItem item_no;
	/** 件号描述 */
	public TextItem item_description;
	/** 制造商code **/
	public TextItem item_ManufacturerCode;
	/** 制造商codeId */
	public TextItem item_ManufacturerCodeId;
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
	/**
	 * 折扣
	 */
	public ComboBoxItem item_m_DiscountPercentCode;

	/** 备注 */
	public TextAreaItem item_remark;
	/** 金额输入框 */
	public SpinnerItem item_price;
	/** 用于保存申请单或其他相关信息的ID */
	public TextItem item4Id;

	public IButton item_save;
	public IButton item_new;
	public IButton item_close;
	public HLayout h_btns;

	public ContractItemAddForm(String groupTitle) {
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
					}
				});
	}

	private void buidItems() {
		h_btns = new HLayout();
		h_btns.setLayoutLeftMargin(10);
		h_btns.setMargin(5);
		/**
		 * 初始化清空按钮
		 */
		item_new = new IButton("清空");
		item_new.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.clearValues();
				form.editNewRecord();
				item_save.setTitle("保存");
			}
		});
		/**
		 * 初始化关闭按钮
		 */
		item_close = new IButton("关闭");
		item_close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				model.openedWindow.close();
			}
		});
		/**
		 * 初始化保存按钮
		 */
		item_save = new IButton("保存");
		item_save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int res = BaseBusiness.compareRowNum(model.sheetItemGrid,
						model.pactItemGrid);
				DSOperationType state = form.getSaveOperationType();
				if (res >= 0 && state == DSOperationType.ADD) {
					SC.say("不能添加明细信息，请核对后再试！");
					return;
				}
				if (model.contractID == null) {
					SC.say("请先保存合同基本信息！");
					return;
				}
				sumAmount();
				item4Id.setValue(model.contractID);
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功！");
						form.editNewRecord();
						item_save.setTitle("保存");
						Criteria criteria = new Criteria();
						criteria.setAttribute("salesTemplate.id",
								model.contractID);
						model.pactItemGrid.fetchData(criteria);
					}
				});
			}
		});
		h_btns.addMember(item_save);
		h_btns.addMember(item_new);
		h_btns.addMember(item_close);

		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remarkText");
		item_remark.setColSpan(5);
		item_remark.setTitleVAlign(VerticalAlignment.TOP);

		/**
		 * 初始化金额输入框
		 */
		item_price = new SpinnerItem("price");
		item_price.setDisabled(true);
		/**
		 * 初始化币种下拉列表
		 */
		item_currency = new SelectItem("currency");
		/**
		 * 初始化单价输入框
		 */
		item_unitPrice = new SpinnerItem("unitPrice");
		item_unitPrice.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();
			}
		});
		/**
		 * 初始化单位下拉列表
		 */
		item_unit = new SelectItem("m_UnitOfMeasureCode");
		/**
		 * 初始化数量输入框
		 */
		item_count = new SpinnerItem("quantity");
		item_count.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();
			}
		});
		/**
		 * 初始化适用机型下拉列表
		 */
		item_type = new SelectItem("m_ModelofApplicabilityCode");
		CodeRPCTool.bindModelofApplicabilityCode(item_type);
		item_type.setMultiple(true);
		/**
		 * 初始化关键字输入框
		 */
		item_keys = new TextItem("keyword");
		item_keys.setDisabled(true);
		/**
		 * 初始化随件资料下拉列表
		 */
		item_docs = new SelectItem("m_CertificateType");
		CodeRPCTool.bindCertificateType(item_docs);
		item_docs.setMultiple(true);
		/**
		 * 交货日期
		 */
		deliveryDate = new DateItem("deliveryDate");
		deliveryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		deliveryDate.setUseTextField(true);
		/**
		 * 初始化制造商code输入框
		 */
		item_ManufacturerCode = new TextItem("m_ManufacturerCode.code");
		item_ManufacturerCode.setDisabled(true);
		/**
		 * 初始化制造商id输入框
		 */
		item_ManufacturerCodeId = new TextItem();
		item_ManufacturerCodeId.setName("m_ManufacturerCode.id");
		item_ManufacturerCodeId.setVisible(false);

		/** ata章节号 */
		item_ata = new SelectItem("ata");
		item_ata.setMultiple(true);
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
		/**
		 * 件号描述
		 */
		item_description = new TextItem("description");
		/**
		 * 折扣
		 */
		item_m_DiscountPercentCode = new ComboBoxItem("m_DiscountPercentCode");
		item_m_DiscountPercentCode.setPrompt("1-100的折扣数字");
		item_m_DiscountPercentCode.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();
			}
		});

		item4Id = new TextItem();
		item4Id.setName("salesTemplate.id");
		item4Id.setVisible(false);
		form.setItems(item_no, item_description, item_ManufacturerCode,
				item_ManufacturerCodeId, item_ata, item_keys, item_type,
				item_docs, deliveryDate, item_unit, item_count, item_currency,
				item_unitPrice, item_price, item_m_DiscountPercentCode,
				item_remark, item4Id);
		this.addMember(form);
		this.addMember(h_btns);
	}

	/**
	 * 计算金额
	 */
	private void sumAmount() {
		if (item_count.getValue() != null && item_unitPrice.getValue() != null) {
			int quantity = Integer.parseInt(item_count.getValueAsString());
			double unitPrice = Double.parseDouble(item_unitPrice
					.getValueAsString());
			double m_DiscountPercent = 100;
			if (item_m_DiscountPercentCode.getValue() != null) {
				m_DiscountPercent = Double
						.parseDouble(item_m_DiscountPercentCode
								.getValueAsString());
			}
			item_price
					.setValue(quantity * unitPrice * m_DiscountPercent * 0.01);
		}
	}
}
