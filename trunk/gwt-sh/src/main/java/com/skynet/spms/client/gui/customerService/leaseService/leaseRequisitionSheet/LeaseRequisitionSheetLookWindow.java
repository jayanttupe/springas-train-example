package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet;

import java.util.LinkedHashMap;

import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.DicKey;
import com.skynet.spms.client.gui.base.SuperWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LeaseRequisitionSheetLookWindow extends SuperWindow {

	private static I18n ui = new I18n();
	final LayoutDynamicForm form = new LayoutDynamicForm();

	private LeaseRequisitionSheetBusiness bus = new LeaseRequisitionSheetBusiness();
	private MainModelLocator modelLocator = MainModelLocator.getInstance();
	private LeaseRequisitionSheetItemListGrid itemlist;
	private String leaseId = modelLocator.leaseRequisitionSheetListGrid
			.getSelectedRecord().getAttribute("id");
	private IButton saveItemBtn;

	public LeaseRequisitionSheetLookWindow() {
		setTitle(ui.getB().addFormTitle(
				ui.getM().mod_leaseRequisitionSheeet_name()));
		VLayout v = new VLayout();
		v.setMembers(buildForm(), buildListGrid(), buildLeaseItemForm());
		addMemberToLeft(v);

	}

	// 加载租赁申请单Form
	private Canvas buildForm() {
		VLayout v = new VLayout();
		v.setHeight(150);
		HLayout h1 = new HLayout();
		final LayoutDynamicForm form = new LayoutDynamicForm();

		form.setDataSource(modelLocator.leaseRequisitionSheetListGrid
				.getDataSource());
		form.reset();
		form.editSelectedData(modelLocator.leaseRequisitionSheetListGrid);
		form.setNumCols(6);
		form.setWidth100();
		// 申请单编号
		TextItem item1 = new TextItem();
		item1.setName("requisitionSheetNumber");
		item1.setAttribute("readOnly", true);

		// 优先级
		SelectItem item2 = new SelectItem();
		item2.setName("m_Priority");

		// 飞机机尾号
		DicSelectItem item3 = new DicSelectItem();
		item3.setName("airIdentificationNumber");
		item3.setValueMap(DicKey.AIRCRAFTIDENTIFICATIONNUMBER);

		// 客户名称
		SelectItem item4 = new SelectItem();
		item4.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item4);

		// 联系人
		TextItem item5 = new TextItem();
		item5.setName("linkman");

		// 联系方式
		TextAreaItem item6 = new TextAreaItem();
		item6.setName("contactInformation");
		item6.setRowSpan(2);
		item6.setHeight(80);

		// 备注
		TextAreaItem item7 = new TextAreaItem();
		item7.setName("remarkText");
		item7.setHeight(50);
		form.setFields(item1, item2, item3, item4, item5, item6, item7);
		h1.addMember(form);

		HLayout h2 = new HLayout();
		h2.setHeight(20);

		IButton saveBtn = new IButton("修改");
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say("修改成功!");
							bus.refeshListGrid();
							form.clearValues();
						}
					});
				}
			}
		});

		h2.addMember(saveBtn);
		v.setMembers(h1, h2);
		return v;
	}

	// 加载租赁申请单明细的ListGrid
	private Canvas buildListGrid() {
		VLayout v = new VLayout();
		HLayout h1 = new HLayout();
		h1.setHeight(20);
		Label lbl = new Label("租赁申请单明细");
		h1.addMember(lbl);
		HLayout h2 = new HLayout();
		itemlist = new LeaseRequisitionSheetItemListGrid();
		itemlist
				.setDataSource(modelLocator.LeaseRequisitionSheetItemdataSource);
		itemlist.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				form.reset();
				form.editSelectedData(itemlist);
				saveItemBtn.setDisabled(false);
			}
		});
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", leaseId);
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		itemlist.fetchData(criteria);
		itemlist.drawGrid();
		h2.addMember(itemlist);
		v.setMembers(h1, h2);
		return v;
	}

	// 加载租令申请单明细项的Form
	private Canvas buildLeaseItemForm() {
		VLayout v = new VLayout();
		v.setGroupTitle("租赁申请单明细项");
		v.setIsGroup(true);

		HLayout h1 = new HLayout();

		form.setNumCols(4);
		form.setWidth100();
		form.setDataSource(modelLocator.LeaseRequisitionSheetItemdataSource);
		final TextItem leaseid = new TextItem();
		leaseid.setVisible(false);
		leaseid.setName("leaseRequisitionSheet.id");

		SelectItem item1 = Utils.getPartNumberList();
		item1.setName("partNumber");

		LinkedHashMap<String, String> manufacturerCodeMap = new LinkedHashMap<String, String>();
		manufacturerCodeMap.put("8ce3b81d6a8847e1849e89f22c231f91", "制造商1");
		SelectItem item2 = new SelectItem();
		item2.setName("m_ManufacturerCode.id");
		item2.setValueMap(manufacturerCodeMap);

		TextItem item3 = new TextItem();
		item3.setName("keyword");

		DicSelectItem item4 = new DicSelectItem();
		item4.setName("m_CertificateType");
		item4.setValueMap(DicKey.CERTIFICATETYPE);

		DicSelectItem item5 = new DicSelectItem();
		item5.setName("m_ConditionCode");
		item5.setValueMap(DicKey.CONDITIONCODE);

		DicSelectItem item6 = new DicSelectItem();
		item6.setName("m_ShelfLifeCode");
		item6.setValueMap(DicKey.SHELFLIFECODE);

		DicSelectItem item7 = new DicSelectItem();
		item7.setName("m_ModelofApplicabilityCode");
		item7.setValueMap(DicKey.MODELOFAPPLICABILITYCODE);

		TextItem item8 = new TextItem();
		item8.setName("quantity");

		DateItem item9 = new DateItem();
		item9.setName("startDate");

		DicSelectItem item99 = new DicSelectItem();
		item99.setName("m_UnitOfMeasureCode");
		item99.setValueMap(DicKey.UNITOFMEASURECODE);

		DateItem item10 = new DateItem();
		item10.setName("endDate");

		TextAreaItem item11 = new TextAreaItem();
		item11.setName("remarkText");
		item11.setRowSpan(2);
		item11.setHeight(50);

		TextItem item12 = new TextItem();
		item12.setName("leaseDays");
		form.setFields(leaseid, item1, item2, item3, item4, item5, item6,
				item7, item8, item9, item99, item10, item11, item12);
		h1.addMember(form);
		HLayout h2 = new HLayout();
		h2.setHeight(20);
		saveItemBtn = new IButton("修改明细");
		saveItemBtn.setDisabled(true);
		saveItemBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				leaseid.setValue(leaseId);
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("id", leaseId);
							criteria.addCriteria("_r", String.valueOf(Math
									.random()));
							itemlist.fetchData(criteria);
							itemlist.drawGrid();
							SC.say("修改成功!");
							form.clearValues();

						}
					});
				} else {

				}
			}
		});
		IButton closeBtn = new IButton("关闭");
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		h2.setMembers(saveItemBtn, closeBtn);
		v.setMembers(h1, h2);
		return v;
	}
}
