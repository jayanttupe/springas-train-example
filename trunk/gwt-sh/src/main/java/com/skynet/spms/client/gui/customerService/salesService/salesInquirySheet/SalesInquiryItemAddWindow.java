package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.ui.Image;
import com.skynet.spms.client.gui.base.DicKey;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 询价单明细项添加窗体
 * 
 * @author tqc
 * 
 */
public class SalesInquiryItemAddWindow extends Window {

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public SalesInquiryItemAddWindow(final ListGrid listGrid,
			final SalesInquiryItemListGrid itemListGrid) {
		this.setWidth(600);
		this.setHeight(500);
		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();
		// 获得询价单主键
		final String sheetId = listGrid.getSelectedRecord()
				.getAttributeAsString("id");
		buildItemForm(sheetId);
		form.setDataSource(itemListGrid.getDataSource());

		Button saveBtn = new Button("保存");
		saveBtn.setPadding(6);
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say("保存成功!");
							Criteria criteria = new Criteria();
							SC.say("sheetId");
							criteria.addCriteria("sheetId", sheetId);
							criteria.addCriteria("r",
									String.valueOf(Math.random()));
							itemListGrid.filterData(criteria);
							destroy();
						}
					});
				} else {

				}

			}
		});

		Button cancelBtn = new Button("取消");
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		// 主布局
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();


		HLayout hlForm = new HLayout();
		hlForm.setWidth100();
		hlForm.setLayoutTopMargin(15);
		hlForm.setLayoutLeftMargin(15);
		hlForm.setAlign(Alignment.CENTER);
		hlForm.addMember(form);

		// 图片
		HLayout hlImg = new HLayout();
		hlImg.setWidth("30%");
		Image img = new Image();
		hlImg.addMember(img);
		hlForm.addMember(hlImg);

		v.addMember(hlForm);

		// 操作按钮布局
		HLayout hlayout = new HLayout();
		hlayout.setLayoutTopMargin(30);
		hlForm.setLayoutLeftMargin(10);
		hlayout.setWidth100();
		hlayout.setMargin(5);
		hlayout.setPadding(5);
		hlayout.setAlign(Alignment.CENTER);
		hlayout.addMember(saveBtn);
		hlayout.addMember(cancelBtn);
		v.addMember(hlayout);
		this.addItem(v);
	}

	/**
	 * 构建明细表单
	 */
	private void buildItemForm(String sheetId) {
		form.setNumCols(2);
		form.setTop(5);
		form.setWidth("70%");

		HiddenItem sheetItemId = new HiddenItem();
		sheetItemId.setName("salesInquirySheet.id");
		sheetItemId.setValue(sheetId);

		// 件号搜索
		SelectItem item1 = Utils.getPartNumberList();
		item1.setTitle("件号");
		item1.setName("partNumber");
		item1.setTitleAlign(Alignment.LEFT);
		FormItemIcon fiIcon = new FormItemIcon();
		fiIcon.setPrompt("查看技术目录");
		item1.setIcons(fiIcon);
		item1.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent event) {

			}
		});
		item1.setRequired(true);

		DicSelectItem item2 = new DicSelectItem();
		item2.setTop(8);
		item2.setTitle("机型识别码");
		item2.setValueMap(DicKey.MODELOFAPPLICABILITYCODE);
		item2.setName("m_ModelofApplicabilityCode");
		item2.setTitleAlign(Alignment.LEFT);
		item2.setRequired(true);

		// 测试数据制造商代码
		LinkedHashMap<String, String> manufacturerCodeMap = new LinkedHashMap<String, String>();
		manufacturerCodeMap.put("8ce3b81d6a8847e1849e89f22c231f91", "制造商1");
		SelectItem item3 = new SelectItem();
		item3.setTitle("制造商代码");
		item3.setValueMap(manufacturerCodeMap);
		item3.setTitleAlign(Alignment.LEFT);
		item3.setName("m_ManufacturerCode");
		item3.setRequired(true);

		DicSelectItem item4 = new DicSelectItem();
		item4.setTitle("优先级");
		item4.setValueMap(DicKey.PRIORITY);
		item4.setName("m_Priority");
		item4.setTitleAlign(Alignment.LEFT);
		item4.setRequired(true);

		DicSelectItem item5 = new DicSelectItem();
		item5.setTitle("备件状态代码");
		item5.setValueMap(DicKey.CONDITIONCODE);
		item5.setTitleAlign(Alignment.LEFT);
		item5.setName("m_ConditionCode");
		item5.setRequired(true);

		DicSelectItem item6 = new DicSelectItem();
		item6.setTitle("国际货币代码");
		item6.setValueMap(DicKey.INTERNATIONALCURRENCYCODE);
		item6.setTitleAlign(Alignment.LEFT);
		item6.setName("m_InternationalCurrencyCode");
		item6.setRequired(true);

		DicSelectItem item7 = new DicSelectItem();
		item7.setTitle("单位测量代码");
		item7.setValueMap(DicKey.UNITOFMEASURECODE);
		item7.setName("m_UnitOfMeasureCode");
		item7.setTitleAlign(Alignment.LEFT);
		item7.setRequired(true);

	
		SpinnerItem item8 = new SpinnerItem();
		item8.setStep(1);  
		item8.setMin(0);
		item8.setTitle("数量");
		item8.setWidth(150);
		item8.setName("quantity");
		item8.setTitleAlign(Alignment.LEFT);
		item8.setRequired(true);

		DateItem item9 = new DateItem();
		item9.setUseTextField(true);
		item9.setTitle("交货日期");
		item9.setName("partDeliveryDate");
		item9.setRequired(true);
		item9.setTitleAlign(Alignment.LEFT);

		DicSelectItem item10 = new DicSelectItem();
		item10.setTitle("证书类型");
		item10.setValueMap(DicKey.CERTIFICATETYPE);
		item10.setName("m_CertificateType");
		item10.setTitleAlign(Alignment.LEFT);
		item10.setRequired(true);

		form.setFields(sheetItemId, item1, item2, item3, item5, item6, item7,
				item4, item8, item9, item10);
	}

}
