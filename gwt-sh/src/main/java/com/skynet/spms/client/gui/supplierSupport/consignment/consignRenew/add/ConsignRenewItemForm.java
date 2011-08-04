package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
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

public class ConsignRenewItemForm extends VLayout {
	
	/**
	 * 设置form高度、宽度、绑定数据源
	 * 
	 * @param groupTitle
	 */
	public ConsignRenewItemForm(String groupTitle) {
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
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNRENEW,
				DSKey.S_CONSIGNRENEWITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						initFields();
						initH_btns();
						build();
					}
				});
	}

	public ConsignRenewModel model = ConsignRenewModel.getInstance();
	public LayoutDynamicForm form;
	private I18n ui = new I18n();
	/** 件号 **/
	public SelectItem item_no;
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

	public IButton item_save;
	public IButton item_new;
	public IButton item_close;
	public HLayout h_btns;

	/**
	 * 将表单和按钮组添加进来
	 */
	public void build() {
		this.addMember(form);
		this.addMember(h_btns);
	}

	/**
	 * 初始化按钮组
	 */
	public void initH_btns() {
		h_btns = new HLayout();
		h_btns.setLayoutLeftMargin(10);
		h_btns.setMargin(5);
		/**
		 * 清空
		 */
		item_new = new IButton(ui.getM().btnClean());
		item_new.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.clearValues();
				form.editNewRecord();
				item_save.setTitle(ui.getM().btnSaveItem());
			}
		});
		/**
		 * 初始化关闭按钮
		 */
		item_close = new IButton(ui.getM().btnClose());
		item_close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				model.openedWindow.close();
			}
		});
		/**
		 * 初始化保存按钮
		 */
		item_save = new IButton(ui.getM().btnSaveItem());
		item_save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int res = BaseBusiness.compareRowNum(model.pactItemGrid,
						model.sheetItemGrid);
				DSOperationType state = form.getSaveOperationType();
				if (res >= 0 && state == DSOperationType.ADD) {
					//不能添加明细信息，请核对后再试！
					SC.say(ui.getM().consignRenew_msg_notSaveItem());
					return;
				}

				if (model.consignRenewId == null) {
					//"请先保存申请单信息！"
					SC.say(ui.getM().msgFirstSaveSheet());
					return;
				}
				sumAmount();
				item4Id.setValue(model.consignRenewId);
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						//保存成功！
						SC.say(ui.getM().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						criteria.setAttribute("consignRenewId",
								model.consignRenewId);
						model.sheetItemGrid.fetchData(criteria);
						form.editNewRecord();
						item_save.setTitle(ui.getM().btnSaveItem());
					}
				});
			}
		});
		h_btns.addMember(item_save);
		h_btns.addMember(item_new);
		h_btns.addMember(item_close);
	}

	/**
	 * 初始化表单元素
	 */
	public void initFields() {
		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remarkText"/* , "备注" */);
		item_remark.setTitleVAlign(VerticalAlignment.TOP);
		item_remark.setColSpan(3);
		item_remark.setRowSpan(5);
		/**
		 * 初始化金额输入框
		 */
		item_price = new SpinnerItem("price"/* , "金额" */);
		item_price.setDisabled(true);
		/**
		 * 初始化币种下拉列表
		 */
		item_currency = new SelectItem("currency"/* , "币种" */);
		/**
		 * 初始化单价输入框
		 */
		item_unitPrice = new SpinnerItem("unitPriceAmount"/* , "单价" */);
		item_unitPrice.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();				
			}
		});
		/**
		 * 初始化单位下拉列表
		 */
		item_unit = new SelectItem("m_UnitOfMeasureCode"/* , "单位" */);
		/**
		 * 初始化数量输入框
		 */
		item_count = new SpinnerItem("quantity"/* , "数量" */);
		item_count.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();				
			}
		});
		/**
		 * 初始化包装代码下拉列表
		 */
		item_pakageNo = new SelectItem("m_PackagingCode"/* , "包装代码" */);
		/**
		 * 初始化使用机型下拉列表
		 */
		item_type = new SelectItem("m_ModelofApplicabilityCode"/* , "适用机型" */);
		item_type.setMultiple(true);
		CodeRPCTool.bindModelofApplicabilityCode(item_type);
		item_type.setDisabled(true);
		/**
		 * 初始化备件状态代码下拉列表
		 */
		item_state = new SelectItem("m_ConditionCode"/* , "备件状态代码" */);
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
		CodeRPCTool.bindCertificateType(item_docs);
		/**
		 * 初始化件号输入框
		 */
		item_no = Utils.getPartNumberList();
		item_no.setStartRow(true);
		item_no.setName("partNumber");
		item_no.setType("comboBox");
		item_no.setRequired(true);
		item_no.setDisabled(true);
		FormItemIcon btnPartItem = new FormItemIcon();
		btnPartItem.setPrompt(ui.getM().mod_showpartdocument());
		item_no.setIcons(btnPartItem);

		item4Id = new TextItem();
		item4Id.setName("consignRenewId");
		item4Id.setVisible(false);
		addItems2Form();
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
		form.setItems(item_no, item_type, item_keys, item_pakageNo, item_state,
				item_docs, item_remark, item_count, item_unit, item_unitPrice,
				item_currency, item_price, item4Id);
	}
	
	/**
	 * 计算金额
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
