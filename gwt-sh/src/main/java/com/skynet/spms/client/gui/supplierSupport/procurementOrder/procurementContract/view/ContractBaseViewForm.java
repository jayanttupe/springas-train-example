package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author gqr
 * 
 */
public class ContractBaseViewForm extends VLayout {
	public LayoutDynamicForm form = new LayoutDynamicForm();
	private ProcurementContractModelLocator pactMode = ProcurementContractModelLocator
			.getInstance();

	public ContractBaseViewForm() {
		form.setNumCols(6);
		form.setSaveOperationType(DSOperationType.UPDATE);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(pactMode.contractID);
						criteria.setAttribute("id", builder.toString());
						form.fetchData(criteria);
						buildFormFields();
						build();
						Utils.setReadOnlyForm(form);
					}
				});

	}

	/** 合同编号输入框 */
	public TextItem contractNumberItem;
	/** 采购指令输入框 */
	public TextItem item_code;
	/** 优先级下拉列表 */
	public SelectItem priorityItem;
	/** 供应商下拉列表 */
	public SelectItem sellerItem;
	/** 联系人 */
	public TextItem sellerContactManItem;
	/** 飞机尾号 */
	public SelectItem aircraftIdentificationNumberItem;
	/** 联系方式 */
	public TextItem linkWayItem;
	/** 交货方式 */
	public SelectItem deliveryTypeItem;
	/** 是否由客户指定运代 复选框 */
	public CheckboxItem isSellerFreightAgentItem;
	/** 运输方式下拉列表 */
	public SelectItem transportationCodeItem;
	/** 运代商输入框 */
	public SelectItem m_CarrierNameItem;
	/** 运代商联系人输入框 */
	public TextItem appointForwarderLinkmanItem;
	/** 贸易方式下拉列表 */
	public SelectItem tradeMethodsItem;
	/** 运代商联系方式输入框 */
	public TextItem appointForwarderContactItem;
	/** GTA协议 **/
	public SelectItem gtaItem;
	/** 支付方式 **/
	public SelectItem paymentItem;
	/** 支付说明 **/
	public TextItem zhiFuShuoMingItem;
	/** 特殊条款框输入域 **/
	public TextAreaItem extraProvisionItem;
	/** 备注输入框 **/
	public TextAreaItem remarkItem;
	/** 制定人输入框 **/
	public TextItem makeByItem;
	/** 项数总计入框 **/
	public TextItem totalCountItem;
	/** 金额总计 **/
	public TextItem totalPriceUnitItem;

	/**
	 * 将表单和按钮组添加进来
	 */
	public void build() {
		this.addMember(form);
	}

	public void buildFormFields() {
		/**
		 * 初始化 合同编号
		 */
		contractNumberItem = new TextItem("contractNumber");
		contractNumberItem.setDisabled(true);
		contractNumberItem.setValue("业务编号系统自动生成");
		/**
		 * 初始化 采购指令号
		 */
		item_code = new TextItem("procurementPlanNumber");
		item_code.setDisabled(true);
		item_code.setValue(pactMode.orderNumber);

		/**
		 * 初始化 供应商下拉列表
		 */
		sellerItem = new SelectItem();
		sellerItem.setName("seller.id");
		CodeRPCTool.bindData(CodeRPCTool.SUPPLIERCODE, sellerItem);
		/**
		 * 初始化 联系人
		 * */
		sellerContactManItem = new TextItem("sellerContactMan");
		/**
		 * 初始化 飞机尾号
		 * */
		aircraftIdentificationNumberItem = new SelectItem();
		aircraftIdentificationNumberItem
				.setName("m_AircraftIdentificationNumber");
		/**
		 * 初始化 联系方式
		 * */
		linkWayItem = new TextItem("m_CarrierContact");
		linkWayItem.setColSpan(3);
		/**
		 * 初始化 交货方式
		 * */
		deliveryTypeItem = new SelectItem();
		deliveryTypeItem.setName("deliveryType");

		/**
		 * 初始化 优先级下拉列表
		 */
		priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");
		/**
		 * 初始化 是否由客户指定运代 复选框
		 * */
		isSellerFreightAgentItem = new CheckboxItem();
		isSellerFreightAgentItem.setName("sellerFreightAgent");
		isSellerFreightAgentItem.setColSpan(3);
		/**
		 * 初始化 运输方式下拉列表
		 * */
		transportationCodeItem = new SelectItem();
		transportationCodeItem.setName("m_TransportationCode");
		/**
		 * 初始化 运代商下拉框
		 * */
		m_CarrierNameItem = new SelectItem();
		m_CarrierNameItem.setName("m_CarrierName.id");
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE, m_CarrierNameItem);
		/**
		 * 初始化 运代商联系人输入框
		 * */
		appointForwarderLinkmanItem = new TextItem("appointForwarderLinkman");
		/**
		 * 初始化 贸易方式下拉列表
		 * */
		tradeMethodsItem = new SelectItem();
		tradeMethodsItem.setName("m_TradeMethods");
		/**
		 * 初始化 运代商联系方式输入框
		 * */
		appointForwarderContactItem = new TextItem("appointForwarderContact");
		appointForwarderContactItem.setColSpan(3);
		/**
		 * 初始化 初始化GTA协议下拉列表
		 */
		gtaItem = new SelectItem();
		gtaItem.setName("gta");
		gtaItem.setMultiple(true);
		DataSourceTool dsTool = new DataSourceTool();
		dsTool.onCreateDataSource(DSKey.C_REPAIRREQUISITIONSHEET,
				DSKey.C_CUSTOMERREPAIRINSORDER_GTA_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						gtaItem.setOptionDataSource(dataSource);
						gtaItem.fetchData();
					}
				});
		/**
		 * 初始化 支付方式
		 * **/
		paymentItem = new SelectItem();
		paymentItem.setName("m_Payment");
		/**
		 * 初始化 支付说明
		 * **/
		zhiFuShuoMingItem = new TextItem("paymentSM");
		zhiFuShuoMingItem.setColSpan(3);
		/**
		 * 初始化 特殊条件框输入域
		 */
		extraProvisionItem = new TextAreaItem();
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		/**
		 * 初始化 备注输入框
		 */
		remarkItem = new TextAreaItem();
		remarkItem.setName("remarkText");
		/**
		 * 初始化 制定人输入框
		 */
		makeByItem = new TextItem();
		makeByItem.setName("makeBy");
		/**
		 * 初始化 初始化项数总计
		 */
		totalCountItem = new TextItem("totalCount");
		/**
		 * 初始化 初始化金额总计
		 */
		totalPriceUnitItem = new TextItem("extendedValueTotalAmount");
		addItems2Form();
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
		form.setFields(contractNumberItem, item_code, priorityItem, sellerItem,
				sellerContactManItem, aircraftIdentificationNumberItem,
				linkWayItem, deliveryTypeItem, isSellerFreightAgentItem,
				transportationCodeItem, m_CarrierNameItem,
				appointForwarderLinkmanItem, tradeMethodsItem,
				appointForwarderContactItem, gtaItem, paymentItem,
				zhiFuShuoMingItem, extraProvisionItem, remarkItem, makeByItem,
				totalCountItem, totalPriceUnitItem);
	}

}
