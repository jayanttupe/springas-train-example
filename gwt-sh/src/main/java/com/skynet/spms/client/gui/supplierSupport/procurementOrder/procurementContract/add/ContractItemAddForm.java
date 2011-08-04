package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.SuppliersParityBoundWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同明细面板
 * 
 * @author fl
 * 
 */
public class ContractItemAddForm extends VLayout {
	private I18n ui = new I18n();
	private ProcurementContractModelLocator model = ProcurementContractModelLocator
			.getInstance();
	public LayoutDynamicForm form;
	/** 件号 · **/
	public TextItem partNumberItem;
	/** 供应商比价 **/
	public TextItem gongYSpriceItem;
	/** ATA章节号· **/
	public TextItem ataNumItem;
	/** 制造商id */
	public TextItem m_ManufacturerIdItem;
	/** 制造商code· **/
	public TextItem m_ManufacturerCodeItem;
	/** 关键字· **/
	public TextItem item_keys;
	/** 适用机型 **/
	public SelectItem modelofApplicabilityCode;
	/** 交货日期 **/
	public DateItem deliveryTypeDate;
	/** 随件资料 **/
	public SelectItem m_CertificateTypeItem;
	/** 采购数量 **/
	public SpinnerItem quantityItem;
	/** 单位 · **/
	public SelectItem m_UnitOfMeasureCodeItem;
	/** 采购单价 **/
	public SpinnerItem item_unitPrice;
	/** 币种 · **/
	public SelectItem currencyItem;
	/** 采购金额· **/
	public SpinnerItem priceItem;
	/** 备注 */
	public TextAreaItem remarkTextItem;
	
	public  TextItem suppliersParityItem;

	/** 用于保存申请单或其他相关信息的ID */
	public TextItem item4Id;

	public IButton item_save;
	public IButton item_new;
	public IButton item_close;
	public HLayout h_btns;
	private BaseListGrid itemGrid;

	public ContractItemAddForm(String groupTitle, BaseListGrid grid) {
		this.itemGrid = grid;
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
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACTITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						build();
					}
				});
	}

	private void build() {
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
				item_save.setTitle("保存明细");
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
		item_save = new IButton("保存明细");
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
				item4Id.setValue(model.contractID);
				sumAmount();
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						form.editNewRecord();
						item_save.setTitle("保存明细");
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						criteria.setAttribute("template.id", model.contractID);
						itemGrid.fetchData(criteria);
					}
				});
			}

		});

		h_btns.addMember(item_save);
		h_btns.addMember(item_new);
		h_btns.addMember(item_close);

		/**
		 * 件号 ·
		 **/
		partNumberItem = new TextItem();
		partNumberItem.setName("partNumber");
		partNumberItem.setColSpan(2);
		partNumberItem.setDisabled(true);
		/**
		 * 供应商比价 未完成！！！！！！！！！ TODO
		 **/
		gongYSpriceItem = new TextItem();
		gongYSpriceItem.setName("gongYSprice");
		gongYSpriceItem.setColSpan(2);
		gongYSpriceItem.setVisible(false);
		/**
		 * ATA章节号·
		 **/
		ataNumItem = new TextItem();
		ataNumItem.setName("ata");
		ataNumItem.setColSpan(2);
		ataNumItem.setDisabled(true);
		/**
		 * 制造商code·
		 **/
		m_ManufacturerCodeItem = new TextItem();
		m_ManufacturerCodeItem.setName("m_ManufacturerCode.code");
		m_ManufacturerCodeItem.setColSpan(2);
		m_ManufacturerCodeItem.setDisabled(true);
		/**
		 * 制造商id
		 */
		m_ManufacturerIdItem = new TextItem();
		m_ManufacturerIdItem.setName("m_ManufacturerCode.id");
		m_ManufacturerIdItem.setVisible(false);
		/**
		 * 关键字·
		 **/
		item_keys = new TextItem();
		item_keys.setName("keyword");
		item_keys.setColSpan(2);
		item_keys.setDisabled(true);
		/**
		 * 适用机型
		 **/
		modelofApplicabilityCode = new SelectItem();
		modelofApplicabilityCode.setName("m_ModelofApplicabilityCode");
		modelofApplicabilityCode.setMultiple(true);
		modelofApplicabilityCode.setColSpan(2);
		CodeRPCTool.bindModelofApplicabilityCode(modelofApplicabilityCode);
		/**
		 * 交货日期
		 **/
		deliveryTypeDate = new DateItem();
		deliveryTypeDate.setName("deliveryDate");
		deliveryTypeDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		deliveryTypeDate.setUseTextField(true);
		deliveryTypeDate.setColSpan(2);
		/**
		 * 随件资料
		 **/
		m_CertificateTypeItem = new SelectItem();
		m_CertificateTypeItem.setName("m_CertificateType");
		m_CertificateTypeItem.setMultiple(true);
		m_CertificateTypeItem.setColSpan(2);
		CodeRPCTool.bindCertificateType(m_CertificateTypeItem);
		/**
		 * 采购数量
		 **/
		quantityItem = new SpinnerItem();
		quantityItem.setName("quantity");
		quantityItem.setColSpan(2);
		quantityItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();
			}
		});
		;
		/**
		 * 单位 ·
		 **/
		m_UnitOfMeasureCodeItem = new SelectItem();
		m_UnitOfMeasureCodeItem.setName("m_UnitOfMeasureCode");
		m_UnitOfMeasureCodeItem.setColSpan(2);
		m_UnitOfMeasureCodeItem.setDisabled(true);
		/**
		 * 采购单价
		 **/
		item_unitPrice = new SpinnerItem();
		item_unitPrice.setName("unitPrice");
		item_unitPrice.setColSpan(2);
		item_unitPrice.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				sumAmount();
			}
		});
		/**
		 * 币种 ·
		 **/
		currencyItem = new SelectItem();
		currencyItem.setName("currency");
		currencyItem.setColSpan(2);
		currencyItem.setDisabled(true);
		/**
		 * 采购金额·
		 **/
		priceItem = new SpinnerItem();
		priceItem.setName("amount");
		priceItem.setColSpan(2);
		priceItem.setDisabled(true);
		/**
		 * 备注
		 */
		remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");
		remarkTextItem.setColSpan(2);
		/**
		 * 合同主键
		 */
		item4Id = new TextItem();
		item4Id.setName("template.id");
		item4Id.setVisible(false);
		
		suppliersParityItem=new TextItem();
		suppliersParityItem.setName("suppliersParityItemIds");
		suppliersParityItem.setVisible(false);
		
		/***
		 * 供应商name
		 */
		final TextAreaItem supplierNameItem =new TextAreaItem();
		supplierNameItem.setColSpan(2);
		supplierNameItem.setName("suppliersParityItemNames");
		supplierNameItem.setTitle("比价供应商");
		FormItemIcon suppliorItemIcon = new FormItemIcon();
		suppliorItemIcon.setPrompt("单击绑定比价单");
		supplierNameItem.setIcons(suppliorItemIcon);
		supplierNameItem.setAttribute("readOnly", true);
		suppliorItemIcon.addFormItemClickHandler(new FormItemClickHandler() {
			public void onFormItemClick(FormItemIconClickEvent event) {
				SuppliersParityModel model = SuppliersParityModel.getInstance();
				model.suppliersParityItemIds=suppliersParityItem.getValue()+"";
				
				if(partNumberItem.getValue()!=null){
					model.partNumber=partNumberItem.getValue()+"";
				}
				
				SuppliersParityBoundWindow win = new SuppliersParityBoundWindow("绑定比价单",true,null,null,null);
				win.suppliersParityItem=suppliersParityItem;
				win.supplierNameItem=supplierNameItem;
				if(!"".equals(partNumberItem.getValue())){
					ShowWindowTools
					.showWindow(supplierNameItem.getPageRect(), win, 200);
				}else{
					SC.say("请先选择备件");
				}
			}
		});
		
		
		
		form.setItems(partNumberItem, gongYSpriceItem, ataNumItem,

				m_ManufacturerCodeItem, m_ManufacturerIdItem, item_keys,
				modelofApplicabilityCode, deliveryTypeDate,
				m_CertificateTypeItem, quantityItem, m_UnitOfMeasureCodeItem,
				item_unitPrice, currencyItem, priceItem, remarkTextItem,
				item4Id,suppliersParityItem,supplierNameItem);
		this.addMember(form);
		this.addMember(h_btns);
	}

	/**
	 * 计算采购金额
	 */
	private void sumAmount() {
		if (quantityItem.getValue() != null
				&& item_unitPrice.getValue() != null) {
			int quantity = Integer.parseInt(quantityItem.getValueAsString());
			double unitPrice = Double.parseDouble(item_unitPrice
					.getValueAsString());
			priceItem.setValue(quantity * unitPrice);
		}
	}
}
