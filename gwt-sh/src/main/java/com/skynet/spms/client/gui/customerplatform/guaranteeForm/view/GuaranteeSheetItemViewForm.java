package com.skynet.spms.client.gui.customerplatform.guaranteeForm.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerplatform.guaranteeForm.model.GuaranteeModelLocator;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class GuaranteeSheetItemViewForm extends VLayout {
	public GuaranteeSheetItemViewForm(String groupTitle, BaseListGrid itemGrid) {
		this.setHeight100();
		this.setWidth100();
		this.setGroupTitle(groupTitle);
		this.setIsGroup(true);
		this.setLayoutLeftMargin(10);
		/**
		 * 初始化表单
		 */
		form = new LayoutDynamicForm();
		form.setWidth100();
		form.setHeight100();
		form.setNumCols(6);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_GUARANTEESHEET,
				DSKey.C_GUARANTEESHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						addItems2Form();
						Utils.setReadOnlyForm(form);
					}
				});
	}

	public LayoutDynamicForm form;
	private SelectItem item_time;
	/** 申请单编号 */
	private TextItem sheetId;
	/** 编号 件号 **/
	public SelectItem item_no;
	/** 制造商 **/
	public SelectItem item_made;
	/** 序号或批号 **/
	public TextItem item_order;
	/** 随件资料 */
	public SelectItem item_docs;
	/** 关键字 **/
	public TextItem item_keys;
	/** 货架寿命 */
	public SelectItem item_age;
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
	/** 备注 */
	public TextAreaItem item_remark;

	public IButton item_close;
	public HLayout h_btns;
	private GuaranteeModelLocator modelLocator = GuaranteeModelLocator
			.getInstance();

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {

		/**
		 * 初始化按钮组
		 */
		h_btns = new HLayout();
		h_btns.setLayoutLeftMargin(10);
		h_btns.setMargin(5);

		/**
		 * 初始化关闭按钮
		 */
		item_close = new IButton("关闭");
		item_close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modelLocator.openedWindow.close();
			}
		});

		/**
		 * 将操作按钮添加到按钮组
		 */
		h_btns.addMember(item_close);

		item_time = new SelectItem("m_WarrantyTimeCycleReferenceCode"/*
																	 * ,
																	 * "担保时间/循环代码"
																	 */);

		sheetId = new TextItem();
		sheetId.setName("sheet.id");
		sheetId.setVisible(false);

		/**
		 * 初始化备注输入域
		 */
		item_remark = new TextAreaItem("remarkText"/* , "备注" */);
		item_remark.setColSpan(5);
		item_remark.setTitleVAlign(VerticalAlignment.TOP);

		/**
		 * 初始化单位下拉列表
		 */
		item_unit = new SelectItem("m_UnitOfMeasureCode"/* , "单位" */);

		/**
		 * 初始化数量输入框
		 */
		item_count = new SpinnerItem("quantity"/* , "数量" */);

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
		/**
		 * 初始化备件状态代码下拉列表
		 */
		item_state = new SelectItem("m_ConditionCode"/* , "备件状态代码" */);

		/**
		 * 初始化货架寿命下拉列表
		 */
		item_age = new SelectItem("m_ShelfLifeCode"/* , "货架寿命" */);

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
		 * 初始化序号/批号输入框
		 */
		item_order = new TextItem("orderNum"/* , "序号/批号" */);

		/**
		 * 初始化制造商下拉列表
		 */
		item_made = new SelectItem("m_ManufacturerCode.id"/* , "制造商" */);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item_made);
		item_made.setDisabled(true);
		/**
		 * 初始化件号输入框
		 */
		item_no = Utils.getPartNumberList();
		item_no.setStartRow(true);
		item_no.setName("partNumber");
		item_no.setRequired(true);
		item_no.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				PartInfoVO vo = Utils.getPartInfoVO(item_no);
				item_keys.setValue(vo.getKeyword());// 关键字
				item_made.setValue(vo.getManufacturerCodeId());// 制造商
				if (vo.getSuitableAircraftModel() != null) {
					String[] types = vo.getSuitableAircraftModel().split(",");
					item_type.setValues(types);// 适用机型
				}
				item_unit.setValue(vo.getUnitOfMeasureCode());// 单位
			}
		});

		form.setItems(item_no, item_made, item_order, item_keys, item_docs,
				item_state, item_age, item_time, item_type, item_count,
				item_pakageNo, item_unit, item_remark, sheetId);

		/**
		 * 将表单和按钮组添加进来
		 */
		this.addMember(form);
		this.addMember(h_btns);
	}

}
