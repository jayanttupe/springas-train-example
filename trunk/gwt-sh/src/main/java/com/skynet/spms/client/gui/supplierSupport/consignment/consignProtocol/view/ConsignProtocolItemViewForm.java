package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 寄售合同明细项表单
 * 
 * @author fl
 * 
 */
public class ConsignProtocolItemViewForm extends VLayout {

	private I18n ui = new I18n();
	private ConsignProtocolModel model = ConsignProtocolModel.getInstance();
	public LayoutDynamicForm form;
	/** 编号 **/
	public SelectItem item_no;
	/** 寄售地点 */
	public TextItem consignPlace;
	/** 随件资料 */
	public SelectItem item_docs;
	/** 关键字 **/
	public TextItem item_keys;
	/** 备件状态代码 */
	public SelectItem item_state;
	/** 适用机型 */
	public SelectItem item_type;
	/** 包装代码 */
	public SelectItem item_pakageNo;
	/** 数量 **/
	public SpinnerItem item_count;
	/** 单位下拉列表 */
	public SelectItem item_unit;
	/** 单价 */
	public SpinnerItem item_unitPrice;
	/** 币种 **/
	public SelectItem item_currency;
	/** 金额输入框 */
	public SpinnerItem item_price;
	/** 备注 */
	public TextAreaItem item_remark;
	/** 用于保存申请单或其他相关信息的ID */
	public TextItem item4Id;
	/**
	 * ata
	 */
	public TextItem item_ata;
	/**
	 * 制造商
	 */
	public SelectItem item_manufacturer;
	public IButton item_close;
	public HLayout h_btns;
	public BaseListGrid proItemGrid;

	public ConsignProtocolItemViewForm(String groupTitle,
			final BaseListGrid proItemGrid) {
		this.setHeight(350);
		this.setWidth100();
		this.setOverflow(Overflow.AUTO);
		this.setGroupTitle(groupTitle);
		this.setIsGroup(true);
		this.setLayoutLeftMargin(10);
		this.proItemGrid = proItemGrid;
		form = new LayoutDynamicForm();
		form.setWidth100();
		form.setHeight100();
		form.setNumCols(6);
		DataSourceTool dataSourceTool = new DataSourceTool();
		// 明细数据源
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOLITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);

						build();
						
						Utils.setReadOnlyForm(form);
					}
				});

	}

	private void build() {
		h_btns = new HLayout();
		h_btns.setLayoutLeftMargin(10);
		h_btns.setMargin(5);
		/**
		 * 初始化关闭按钮
		 */
		item_close = new IButton(ui.getM().btnClose());
		item_close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				model.openedWindow.close();
			}
		});
		item4Id = new TextItem();
		item4Id.setName("consignId");
		item4Id.setVisible(false);
		h_btns.addMember(item_close);

		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remarkText"/* , "备注" */);
		item_remark.setColSpan(5);
		item_remark.setTitleVAlign(VerticalAlignment.TOP);
		/**
		 * 寄售地点
		 */
		consignPlace = new TextItem("consignAddr"/* , "寄售地点" */);
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
		 * 初始化包装代码下拉列表
		 */
		item_pakageNo = new SelectItem("m_PackagingCode");
		/**
		 * 初始化使用机型下拉列表
		 */
		item_type = new SelectItem("m_ModelofApplicabilityCode");
		item_type.setMultiple(true);
		CodeRPCTool.bindModelofApplicabilityCode(item_type);
		/**
		 * 初始化备件状态代码下拉列表
		 */
		item_state = new SelectItem("m_ConditionCode");
		/**
		 * 初始化关键字输入框
		 */
		item_keys = new TextItem("keyword");
		/**
		 * 初始化随件资料下拉列表
		 */
		item_docs = new SelectItem("m_CertificateType");
		item_docs.setMultiple(true);
		CodeRPCTool.bindCertificateType(item_docs);
		/**
		 * 初始化件号输入框
		 */
		item_no = Utils.getPartNumberList();
		item_no.setStartRow(true);
		item_no.setName("partNumber");
		item_no.setType("comboBox");
		item_no.setRequired(true);
		FormItemIcon btnPartItem = new FormItemIcon();
		btnPartItem.setPrompt(ui.getM().mod_showpartdocument());
		item_no.setIcons(btnPartItem);
		item_no.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				PartInfoVO vo = Utils.getPartInfoVO(item_no);
				item_ata.setValue(vo.getAtaNumber());// ATA
				item_keys.setValue(vo.getKeyword());// 关键字
				item_manufacturer.setValue(vo.getManufacturerCodeId());// 制造商
				if (vo.getSuitableAircraftModel() != null) {
					String[] types = vo.getSuitableAircraftModel().split(",");
					item_type.setValues(types);// 适用机型
				}
				item_unit.setValue(vo.getUnitOfMeasureCode());// 单位
			}
		});
		item_ata = new TextItem("ata");
		item_ata.setDisabled(true);

		item_manufacturer = new SelectItem("m_ManufacturerCode.id");
		item_manufacturer.setDisabled(true);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item_manufacturer);

		form.setItems(item_no, item_manufacturer, item_ata, item_keys,
				consignPlace, item_type, item_state, item_docs, item_pakageNo,
				item_unitPrice, item_currency, item_price, item_count,
				item_unit, item_remark, item4Id);
		this.addMember(form);
		this.addMember(h_btns);
	}

	/**
	 * 计算采购金额
	 */
	private void sumAmount() {
		if (item_count.getValue() != null && item_unitPrice.getValue() != null) {
			int quantity = Integer.parseInt(item_count.getValueAsString());
			double unitPrice = Double.parseDouble(item_unitPrice
					.getValueAsString());
			item_price.setValue(quantity * unitPrice);
		}
	}

}
