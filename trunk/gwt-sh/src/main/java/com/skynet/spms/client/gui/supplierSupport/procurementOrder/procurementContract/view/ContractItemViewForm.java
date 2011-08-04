package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
/**
 * 合同明细表
 * @author gqr
 *
 */
public class ContractItemViewForm extends VLayout {
	public LayoutDynamicForm form;
	/** 件号 · **/
	public TextItem partNumberItem;
	/** 供应商比价 **/
	public TextItem gongYSpriceItem;
	/** ATA章节号· **/
	public TextItem ataNumItem;
	/** 制造商· **/
	public TextItem m_ManufacturerCodeItem;
	/** 关键字· **/
	public TextItem item_keyword;
	/** 适用机型 **/
	public SelectItem shiYongJiXingItem;
	/** 交货日期 **/
	public DateItem deliveryDate;
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

	/** 用于保存申请单或其他相关信息的ID */
	public TextItem item4Id;

	public ContractItemViewForm(String groupTitle, BaseListGrid grid) {
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
						Utils.setReadOnlyForm(form);
					}
				});
	}

	private void build() {
		/**
		 * 件号 ·
		 **/
		partNumberItem = new TextItem();
		partNumberItem.setName("partNumber");
		partNumberItem.setColSpan(2);
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
		/**
		 * 制造商·
		 **/
		m_ManufacturerCodeItem = new TextItem();
		m_ManufacturerCodeItem.setName("m_ManufacturerCode.code");
		m_ManufacturerCodeItem.setColSpan(2);
		/**
		 * 关键字·
		 **/
		item_keyword = new TextItem();
		item_keyword.setName("keyword");
		item_keyword.setColSpan(2);
		/**
		 * 适用机型
		 **/
		shiYongJiXingItem = new SelectItem();
		shiYongJiXingItem.setName("m_ModelofApplicabilityCode");
		shiYongJiXingItem.setColSpan(2);
		/**
		 * 交货日期
		 **/
		deliveryDate = new DateItem();
		deliveryDate.setName("deliveryDate");
		deliveryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		deliveryDate.setUseTextField(true);
		deliveryDate.setColSpan(2);
		;
		/**
		 * 随件资料
		 **/
		m_CertificateTypeItem = new SelectItem();
		m_CertificateTypeItem.setName("m_CertificateType");
		m_CertificateTypeItem.setColSpan(2);
		/**
		 * 采购数量
		 **/
		quantityItem = new SpinnerItem();
		quantityItem.setName("quantity");
		quantityItem.setColSpan(2);
		;
		/**
		 * 单位 ·
		 **/
		m_UnitOfMeasureCodeItem = new SelectItem();
		m_UnitOfMeasureCodeItem.setName("m_UnitOfMeasureCode");
		m_UnitOfMeasureCodeItem.setColSpan(2);
		/**
		 * 采购单价
		 **/
		item_unitPrice = new SpinnerItem();
		item_unitPrice.setName("unitPrice");
		item_unitPrice.setColSpan(2);
		/**
		 * 币种 ·
		 **/
		currencyItem = new SelectItem();
		currencyItem.setName("currency");
		currencyItem.setColSpan(2);
		/**
		 * 采购金额·
		 **/
		priceItem = new SpinnerItem();
		priceItem.setName("amount");
		priceItem.setColSpan(2);
		/**
		 * 备注
		 */
		remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");
		remarkTextItem.setColSpan(2);

		item4Id = new TextItem();
		item4Id.setName("template.id");
		item4Id.setVisible(false);
		form.setItems(partNumberItem, gongYSpriceItem, ataNumItem,
				m_ManufacturerCodeItem, item_keyword, shiYongJiXingItem,
				deliveryDate, m_CertificateTypeItem, quantityItem,
				m_UnitOfMeasureCodeItem, item_unitPrice, currencyItem,
				priceItem, remarkTextItem,item4Id);
		this.addMember(form);
	}

}
