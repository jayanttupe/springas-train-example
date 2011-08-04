package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.view;

import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.LeaseRequisitionSheetItemListGrid;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.i18n.I18n;

public class LeaseRequisitionSheetViewWindow extends BaseWindow {
	private static I18n ui = new I18n();
	private MainModelLocator modelLocator;
	private LeaseRequisitionSheetBusiness business = new LeaseRequisitionSheetBusiness();
	private LeaseRequisitionSheetItemListGrid itemlist;
	private String leaseId;
	private IButton saveItemBtn;

	TextItem item5;
	TextAreaItem item6;

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		modelLocator = MainModelLocator.getInstance();
		setTitle(ui.getB().viewFormTitle(
				ui.getM().mod_leaseRequisitionSheeet_name()));
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setMembersMargin(2);
		v.setMembers(buildForm(), buildListGrid(), buildLeaseItemForm());
		return v;

	}

	// 加载租赁申请单Form
	private Canvas buildForm() {
		VLayout v = new VLayout();
		v.setHeight("30%");
		v.setWidth100();
		HLayout h1 = new HLayout();
		final LayoutDynamicForm form = new LayoutDynamicForm();
		form.setDataSource(modelLocator.leaseRequisitionSheetListGrid
				.getDataSource());
		form.setNumCols(6);
		form.setWidth100();
		form.setHeight100();
		form.setCellPadding(2);

		form.editSelectedData(modelLocator.leaseRequisitionSheetListGrid);
		// 申请单编号
		TextItem item1 = new TextItem();
		item1.setName("requisitionSheetNumber");
		item1.setValue(ui.getB().msgAutoIdInfo());
		item1.setDisabled(true);

		// 优先级
		SelectItem item2 = new SelectItem();
		item2.setName("m_Priority");

		// 飞机机尾号
		DicSelectItem item3 = new DicSelectItem();
		item3.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item3);

		// 客户
		final SelectItem item4 = new SelectItem();
		item4.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item4);
		item4.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				String id = item4.getValueAsString();
				CodeRPCTool.bindCustomerData(id, item5, item6);
			}
		});

		// 联系人
		item5 = new TextItem();
		item5.setName("linkman");

		// 联系方式
		item6 = new TextAreaItem();
		item6.setName("contactWay");
		item6.setRowSpan(2);
		item6.setHeight("100%");
		item6.setTitleVAlign(VerticalAlignment.TOP);

		// 备注
		TextAreaItem item7 = new TextAreaItem();
		item7.setName("remark");
		item7.setHeight("100%");
		item7.setTitleVAlign(VerticalAlignment.TOP);
		item7.setWidth("100%");
		form.setFields(item1, item2, item3, item4, item5, item6, item7);
		Utils.setReadOnlyForm(form);
		h1.addMember(form);

		HLayout h2 = new HLayout();
		h2.setHeight(20);
		h2.setLayoutLeftMargin(50);
		h2.setMargin(5);
		h2.setLayoutMargin(5);

		IButton saveBtn = new IButton(ui.getB().btnSave());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say(ui.getB().msgAddOpSuccess());
							business.refeshListGrid();
							leaseId = response.getData()[0].getAttribute("id");
						}
					});
				}
			}
		});

		h2.addMember(saveBtn);
		v.addMember(h1);
		return v;
	}

	private LayoutDynamicForm form;

	// 加载租赁申请单明细的ListGrid
	private Canvas buildListGrid() {
		VLayout v = new VLayout();
		v.setHeight("30%");
		v.setWidth100();
		HLayout h1 = new HLayout();
		h1.setHeight(15);
		Label lbl = new Label(ui.getB().leaseRequisitionSheetItems());
		lbl.setHeight(15);
		h1.addMember(lbl);
		HLayout h2 = new HLayout();
		itemlist = new LeaseRequisitionSheetItemListGrid();
		itemlist
				.setDataSource(modelLocator.LeaseRequisitionSheetItemdataSource);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", modelLocator.leaseRequisitionSheetListGrid
				.getSelectedRecord().getAttribute("id"));
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		itemlist.fetchData(criteria);
		itemlist.drawGrid();
		Utils.setListGridHeight(itemlist);

		itemlist.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				form.reset();
				form.editSelectedData(itemlist);
			}
		});

		h2.addMember(itemlist);
		v.setMembers(h1, h2);
		return v;
	}

	SelectItem item2;
	TextItem item3;
	SelectItem item7;
	SelectItem item99;

	// 加载租令申请单明细项的Form
	private Canvas buildLeaseItemForm() {
		VLayout v = new VLayout();

		v.setGroupTitle(ui.getB().leaseRequisitionSheetItems());
		v.setHeight("35%");
		v.setWidth100();
		v.setIsGroup(true);

		HLayout h1 = new HLayout();
		h1.setWidth100();
		h1.setHeight100();
		form = new LayoutDynamicForm();
		form.setNumCols(6);
		form.setWidth100();
		form.setHeight100();
		form.setDataSource(modelLocator.LeaseRequisitionSheetItemdataSource);

		final TextItem leaseid = new TextItem();
		leaseid.setVisible(false);
		leaseid.setName("leaseRequisitionSheet.id");

		final SelectItem item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				PartInfoVO partInfo = Utils.getPartInfoVO(item1);
				item2.setValue(partInfo.getManufacturerCodeId());
				item3.setValue(partInfo.getKeyword());
				item7.setValue(partInfo.getSuitableAircraftModel());
				item99.setValue(partInfo.getUnitOfMeasureCode());
			}
		});

		// 制造商
		item2 = new SelectItem();
		item2.setTitle(ui.getB().m_ManufacturerCode());
		item2.setName("m_ManufacturerCode.id");
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);
		item2.setDisabled(true);

		item3 = new TextItem();
		item3.setName("keyword");
		item3.setDisabled(true);

		DicSelectItem item4 = new DicSelectItem();
		item4.setName("m_CertificateType");
		item4.setMultiple(true);
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, item4);

		DicSelectItem item5 = new DicSelectItem();
		item5.setName("m_ConditionCode");

		item7 = new SelectItem();
		item7.setMultiple(true);
		item7.setName("m_ModelofApplicabilityCode");
		item7.setDisabled(true);

		TextItem item8 = new TextItem();
		item8.setName("quantity");

		final DateItem item9 = new DateItem();
		item9.setName("startDate");
		item9.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item9.setUseTextField(true);

		item99 = new SelectItem();
		item99.setName("m_UnitOfMeasureCode");

		final DateItem item10 = new DateItem();
		item10.setName("endDate");
		item10.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item10.setUseTextField(true);

		TextAreaItem item11 = new TextAreaItem();
		item11.setName("remarkText");
		item11.setRowSpan(2);
		item11.setHeight("100%");
		item11.setTitleVAlign(VerticalAlignment.TOP);

		final TextItem item12 = new TextItem();
		item12.setName("leaseDays");
		item12.setDisabled(true);

		item10.addBlurHandler(new BlurHandler() {
			public void onBlur(BlurEvent event) {

				long starttime = item10.getValueAsDate().getTime();

				long endtime = item9.getValueAsDate().getTime();

				int modDay = 24 * 60 * 60 * 1000;

				int day = (int) ((starttime - endtime) / modDay);
				item12.setValue(day);
			}
		});
		form.setFields(leaseid, item1, item2, item3, item4, item5, /* item6, */
		item7, item8, item9, item99, item10, item11, item12);
		Utils.setReadOnlyForm(form);
		h1.addMember(form);
		HLayout h2 = new HLayout();
		h2.setHeight(20);
		h2.setLayoutLeftMargin(50);
		h2.setLayoutMargin(5);
		saveItemBtn = new IButton(ui.getB().btnSaveItem());
		saveItemBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				leaseid.setValue(leaseId);
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say(ui.getB().msgAddOpSuccess());
							form.editNewRecord();
							itemlist
									.setDataSource(modelLocator.LeaseRequisitionSheetItemdataSource);
							Criteria criteria = new Criteria();
							criteria.addCriteria("id", leaseId);
							criteria.addCriteria("_r", String.valueOf(Math
									.random()));
							itemlist.fetchData(criteria);
							itemlist.drawGrid();
						}
					});
				}
			}
		});
		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		h2.setMembers(saveItemBtn, closeBtn);
		v.addMember(h1);
		return v;
	}

	public LeaseRequisitionSheetViewWindow(String windowTitle, boolean isMax,
			Rectangle pageRect, ListGrid listGrid, String iconUrl) {

		super(windowTitle, isMax, pageRect, listGrid, iconUrl);
	}

}
