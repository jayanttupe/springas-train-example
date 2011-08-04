package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract.update;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SuperWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.skynet.spms.client.gui.supplierSupport.leaseService.business.SSLeaseSheetBusiness;
import com.skynet.spms.client.gui.supplierSupport.leaseService.model.SSMainModelLocator;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 添加窗体
 * 
 * @author tqc
 * 
 */
public class SSLeaseContractUpdateWindow extends SuperWindow {
	private static I18n ui = new I18n();
	private SSMainModelLocator modelLocator = SSMainModelLocator.getInstance();
	private SSLeaseSheetBusiness business = new SSLeaseSheetBusiness();
	private LayoutDynamicForm itemLdf;// 订单明细Form

	private HLayout itemBtnsLayout;// 明细操作按钮
	SelectItem item1;
	SelectItem item2;
	TextItem item3;
	SelectItem item4;
	SelectItem item5;
	SelectItem item8;
	TextItem item9;
	SelectItem item10;
	SelectItem item11;
	SelectItem item12;
	DateItem item13;
	TextAreaItem item14;
	DateItem item15;
	TextItem item16;
	TextItem item17;
	SelectItem item18;
	TextItem item19;
	TextItem item20;
	TextItem item21;
	TextItem item22;
	ListGrid lg;

	public SSLeaseContractUpdateWindow() {
		//修改租赁合同
		this.setTitle(ui.getM().modifyFormTitle(ui.getM().mod_ssLeaseContract_name()));
		
		/** 主面板 * */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		/** 面板1 * */
		TabSet tabSet = new TabSet();
		lg = this.getRightGrid();
		//合同基本信息
		Tab baseTab = new Tab(ui.getM().contract_base_info_title());
		SSContractBaseForm baseForm = new SSContractBaseForm(lg);
		baseTab.setPane(baseForm);

		//地址信息
		Tab addressTab = new Tab(ui.getM().address_info_title());
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));

		//附件信息
		Tab attachmentTab = new Tab(ui.getM().attachment_title());
		attachmentTab.setPane(new AttachmentCanvas());

		//合同条款
		Tab provisionTab = new Tab(ui.getM().contract_provision_title());
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,
				TagEnum.RepairInspectClaimContractTemplate));

		tabSet.setTabs(baseTab, addressTab, attachmentTab, provisionTab);
		tabSet.setHeight(380);
		vmain.addMember(tabSet);
		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3 * */
		VLayout threeView = getProcurementItemView();
		vmain.addMember(threeView);

		addMemberToLeft(vmain);
	}

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();

		VLayout leftLayout = new VLayout();
		Label leftLb = new Label(ui.getM().msgListTitle(ui.getB().leaseContractItems()));
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(ui.getM().msgListTitle(ui.getB().ssLeaseContractItems()));
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(lg);

		h.addMember(leftLayout);
		h.addMember(rightLayout);
		return h;
	}

	// 客户租赁合同明细ListGrid
	private ListGrid getLeftGrid() {
		final ListGrid lg = new ListGrid();
		Utils.setListGridHeight(lg);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT_ITEM,
				DSKey.C_LEASECONTRACT_TIEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						lg.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("id",
								modelLocator.ssleaseContractListGrid
										.getSelectedRecord().getAttribute(
												"customerLeaseContractId"));
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						lg.fetchData(criteria);
						// 件号
						ListGridField field2 = new ListGridField("partNumber");
						// 租赁天数
						ListGridField field4 = new ListGridField("leaseDays");
						// 租赁数量
						ListGridField field5 = new ListGridField("quantity");
						Utils.formatQuantityField(field5);

						lg.setFields(field2, field4, field5);
					}
				});

		// 绑定假数据
		lg.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				item1.setValue(lg.getSelectedRecord()
						.getAttribute("partNumber"));
				item2.setValue(lg.getSelectedRecord().getAttribute(
						"m_ManufacturerCode.id"));
				item3.setValue(lg.getSelectedRecord().getAttribute("keyword"));
				item4.setValue(lg.getSelectedRecord().getAttribute(
						"m_CertificateType"));
				item5.setValue(lg.getSelectedRecord().getAttribute(
						"m_ConditionCode"));
				item8.setValue(lg.getSelectedRecord().getAttribute(
						"m_ModelofApplicabilityCode"));
				item9.setValue(lg.getSelectedRecord().getAttribute("quantity"));
				item10.setValue(lg.getSelectedRecord().getAttribute(
						"m_PackagingCode"));
				item11.setValue(lg.getSelectedRecord().getAttribute(
						"m_UnitOfMeasureCode"));
				item12.setValue(lg.getSelectedRecord().getAttribute(
						"m_InternationalCurrencyCode"));
				item13.setValue(lg.getSelectedRecord().getAttributeAsDate(
						"startDate"));
				item14.setValue(lg.getSelectedRecord().getAttribute(
						"remarkText"));
				item15.setValue(lg.getSelectedRecord().getAttributeAsDate(
						"endDate"));
				item16.setValue(lg.getSelectedRecord()
						.getAttribute("leaseDays"));
				item17.setValue(lg.getSelectedRecord().getAttribute(
						"generalRentOfUnit"));
				item18.setValue(lg.getSelectedRecord().getAttribute(
						"m_UnitOfRent"));
				item19.setValue(lg.getSelectedRecord()
						.getAttribute("factorage"));
				item20.setValue(lg.getSelectedRecord().getAttribute(
						"extendedRentOfUnit"));
				item21.setValue(lg.getSelectedRecord().getAttribute("price"));
				item22.setValue(lg.getSelectedRecord().getAttribute(
						"originalValue"));
			}
		});
		return lg;
	}

	// 租赁合同明细项ListGrid
	private ListGrid getRightGrid() {
		lg = new ListGrid();
		Utils.setListGridHeight(lg);
		lg.setCanRemoveRecords(true);
		lg.setDataSource(modelLocator.SSleaseContractItemDs);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", modelLocator.SSLeaseContractId);
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		lg.fetchData(criteria);
		lg.setAutoFetchData(true);
		// 件号
		ListGridField field2 = new ListGridField("partNumber");
		// 租赁数量
		ListGridField field4 = new ListGridField("quantity");
		Utils.formatQuantityField(field4);
		// 租金总额
		ListGridField field6 = new ListGridField("price");
		Utils.formatPriceField(field6);
		lg.setFields(field2, field4, field6);

		lg.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				itemLdf.reset();
				itemLdf.editSelectedData(lg);
			}
		});
		// 绑定假数据
		return lg;
	}

	// 布局3(明细添加)
	/**
	 * @return
	 */

	private VLayout getProcurementItemView() {

		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getB().ssLeaseContractItems());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setDataSource(modelLocator.SSleaseContractItemDs);
		itemLdf.setNumCols(6);
		itemLdf.setWidth100();

		final TextItem leaseContractId = new TextItem();
		leaseContractId.setVisible(false);
		leaseContractId.setName("leaseContractTemplate.id");

		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				PartInfoVO infoVO = Utils.getPartInfoVO(item1);
				item2.setValue(infoVO.getManufacturerCodeId());
				item3.setValue(infoVO.getKeyword());
				item8.setValue(infoVO.getSuitableAircraftModel());
			}
		});

		// 制造商
		item2 = new SelectItem();
		item2.setTitle(ui.getB().m_ManufacturerCode());
		item2.setName("m_ManufacturerCode.id");
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);

		// 关键字
		item3 = new TextItem();
		item3.setName("keyword");

		// 证书类型
		item4 = new SelectItem();
		item4.setMultiple(true);
		item4.setName("m_CertificateType");
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, item4);

		// 备件状态代码
		item5 = new SelectItem();
		item5.setName("m_ConditionCode");

		// 适用机型
		item8 = new SelectItem();
		item8.setName("m_ModelofApplicabilityCode");
		item8.setDisabled(true);

		// 租赁数量
		item9 = new TextItem();
		item9.setName("quantity");
		item9.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item21.setValue(Float.parseFloat(item17.getValue().toString())
						* Integer.parseInt(item16.getValue().toString())
						* Integer.parseInt(item9.getValue().toString())
						+ Float.parseFloat(item19.getValue().toString()));
			}
		});

		// 包装代码
		item10 = new SelectItem();
		item10.setName("m_PackagingCode");

		// 单位
		item11 = new SelectItem();
		item11.setName("m_UnitOfMeasureCode");

		// 币种
		item12 = new SelectItem();
		item12.setName("m_InternationalCurrencyCode");

		// 租赁开始日期
		item13 = new DateItem();
		item13.setName("startDate");
		item13.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item13.setUseTextField(true);
		item13.addBlurHandler(new BlurHandler() {
			public void onBlur(BlurEvent event) {
				long starttime = item15.getValueAsDate().getTime();

				long endtime = item13.getValueAsDate().getTime();

				int modDay = 24 * 60 * 60 * 1000;

				int day = (int) ((starttime - endtime) / modDay);
				item16.setValue(day);
			}
		});

		// 申请单备注
		item14 = new TextAreaItem();
		item14.setName("remarkText");
		item14.setHeight(50);

		// 租赁结束日期
		item15 = new DateItem();
		item15.setName("endDate");
		item15.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item15.setUseTextField(true);
		item15.addBlurHandler(new BlurHandler() {
			public void onBlur(BlurEvent event) {
				long starttime = item15.getValueAsDate().getTime();

				long endtime = item13.getValueAsDate().getTime();

				int modDay = 24 * 60 * 60 * 1000;

				int day = (int) ((starttime - endtime) / modDay);
				item16.setValue(day);
			}
		});

		// 租赁天数
		item16 = new TextItem();
		item16.setName("leaseDays");
		item16.setDisabled(true);
		item16.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item21.setValue(Float.parseFloat(item17.getValue().toString())
						* Integer.parseInt(item16.getValue().toString())
						* Integer.parseInt(item9.getValue().toString())
						+ Float.parseFloat(item19.getValue().toString()));
			}
		});
		// 标准单位租金
		item17 = new TextItem();
		item17.setName("generalRentOfUnit");
		item17.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item21.setValue(Float.parseFloat(item17.getValue().toString())
						* Integer.parseInt(item16.getValue().toString())
						* Integer.parseInt(item9.getValue().toString())
						+ Float.parseFloat(item19.getValue().toString()));
			}
		});

		// 租金计算单位
		item18 = new SelectItem();
		item18.setName("m_UnitOfRent");

		// 手续费
		item19 = new TextItem();
		item19.setName("factorage");
		item19.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item21.setValue(Float.parseFloat(item17.getValue().toString())
						* Integer.parseInt(item16.getValue().toString())
						* Integer.parseInt(item9.getValue().toString())
						+ Float.parseFloat(item19.getValue().toString()));
			}
		});

		// 延期单位租金
		item20 = new TextItem();
		item20.setName("extendedRentOfUnit");

		// 总租金
		item21 = new TextItem();
		item21.setName("price");

		// 原价值
		item22 = new TextItem();
		item22.setName("originalValue");

		itemLdf.setFields(leaseContractId, item1, item2, item3, item4, item5,
				item8, item9, item10, item11, item12, item13, item15, item16,
				item17, item18, item19, item21, item22, item14);
		v.addMember(itemLdf);

		itemBtnsLayout = new HLayout();
		// 构建操作按钮
		IButton saveBtn = new IButton(ui.getB().btnSaveItem());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				leaseContractId.setValue(modelLocator.SSLeaseContractId);
				itemLdf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						criteria.addCriteria("id",
								modelLocator.SSLeaseContractId);
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						lg.fetchData(criteria);
						business
								.refeshListGrid(modelLocator.ssleaseContractListGrid);
					}
				});
			}
		});
		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		itemBtnsLayout.setMembers(saveBtn, closeBtn);
		v.addMember(itemBtnsLayout);

		return v;
	}

}
