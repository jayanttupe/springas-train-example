package com.skynet.spms.client.gui.customerplatform.guaranteeForm.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerplatform.guaranteeForm.model.GuaranteeModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class GuaranteeSheetForm extends VLayout {
	/**
	 * 初始化表单，并绑定数据源
	 */
	public GuaranteeSheetForm() {
		this.setHeight100();
		this.setWidth100();
		this.setLayoutLeftMargin(10);
		form = new LayoutDynamicForm();
		form.setNumCols(6);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_GUARANTEESHEET,
				DSKey.C_GUARANTEESHEET_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						initFields();
					}
				});

	}
	public I18n ui = new I18n();
	public GuaranteeModelLocator modelLocator = GuaranteeModelLocator
			.getInstance();
	private static final String uploadUrl = "spms/upload.do";
	public LayoutDynamicForm form;

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
	 * 备注
	 */
	public TextAreaItem item_remark;
	public IButton item_save;
	public IButton item_close;
	public HLayout h_btns;

	public void initFields() {

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

			@Override
			public void onClick(ClickEvent event) {
				modelLocator.openedWindow.close();
			}
		});
		/**
		 * 初始化保存按钮
		 */
		item_save = new IButton("保存");
		item_save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {

					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功！！");
						modelLocator.sheetID = response.getData()[0]
								.getAttribute("id");
						modelLocator.defaultUploader.setVisible(true);
						modelLocator.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + modelLocator.sheetID);
						// 刷新主面板表格
						Criteria c = new Criteria();
						modelLocator.mainSheetGrid.fetchData(c);
					}
				});
			}
		});
		/**
		 * 将操作按钮添加到按钮组
		 */
		h_btns.addMember(item_save);
		h_btns.addMember(item_close);

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
		item_no.setValue(ui.getB().msgAutoIdInfo());
		item_no.setAttribute("readOnly", true);
		item_no.setTextBoxStyle("back");
		item_no.setDisabled(true);
		/**
		 * 将表单元素添加到表单中
		 */
		form.setItems(item_no, item_name, item_linkMan, item_linkType,
				item_remark, item_desc);

		this.addMember(form);
		this.addMember(h_btns);
	}

}
