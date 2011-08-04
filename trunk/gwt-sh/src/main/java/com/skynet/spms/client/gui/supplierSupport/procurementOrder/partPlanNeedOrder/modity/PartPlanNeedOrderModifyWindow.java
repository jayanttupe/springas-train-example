package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.modity;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/***
 * 主订单 与 明细 添加页面
 * 
 * @author Tony FANG
 * 
 */
public class PartPlanNeedOrderModifyWindow extends BaseWindow {

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	private static I18n ui = new I18n();
	private static final String uploadUrl = "spms/upload.do";

	public PartPlanNeedOrderModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	public MainModelLocator model;

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model = MainModelLocator.getInstance();
		this.setTitle(ui.getB().modifyFormTitle(
				ui.getM().mod_partPlanNeedOrder_name()));
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setMembersMargin(5);

		/** 明细容器 */
		VLayout detailLayout = new VLayout();
		detailLayout.setLayoutTopMargin(10);
		detailLayout.setHeight("57%");
		detailLayout.setWidth100();
		// 明细Form
		detailLayout.addMember(getDetailForm());

		/** 按钮操作容器 **/
		VLayout btnsLayout = new VLayout();
		btnsLayout.setLayoutMargin(8);
		btnsLayout.setHeight(10);
		btnsLayout.setWidth100();
		btnsLayout.addMember(getSubmitLayout());
		btnsLayout.setBorder("1px solid #E8E8E8");

		/** 附件上传容器 **/
		VLayout attachmentLayout = new VLayout();
		attachmentLayout.setHeight("42%");
		attachmentLayout.setWidth100();
		attachmentLayout.setMargin(10);
		// 附件
		AttachmentCanvas attachmentForm = new AttachmentCanvas();
		attachmentLayout.addMember(attachmentForm);
		vmain.addMember(detailLayout);
		vmain.addMember(btnsLayout);
		vmain.addMember(attachmentLayout);
		return vmain;
	}

	/***
	 * 表单信息
	 * 
	 * @return
	 */
	private LayoutDynamicForm ldf;

	private DynamicForm getDetailForm() {
		ldf = new LayoutDynamicForm();
		ldf.setDataSource(model.partPlanNeedOrderListGrid.getDataSource());
		ldf.reset();
		ldf.editSelectedData(model.partPlanNeedOrderListGrid);
		ldf.setCellPadding(4);
		ldf.setPadding(1);
		ldf.setNumCols(4);
		ldf.setWidth100();
		// 件号
		SelectItem item1 = Utils.getPartNumberList();
		item1.setName("partNumber");

		// 客户识别码
		DicSelectItem item2 = new DicSelectItem();
		item2.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item2);

		// 描述
		TextItem item3 = new TextItem();
		item3.setName("description");

		// 采购需求类别
		DicSelectItem item5 = new DicSelectItem();
		item5.setName("m_ProcurementRequiredType");

		// 优先级
		DicSelectItem item6 = new DicSelectItem();
		item6.setName("m_Priority");

		// 数量
		SpinnerItem item7 = new SpinnerItem();
		item7.setName("quantity");

		// 备注
		TextAreaItem item8 = new TextAreaItem();
		item8.setName("remarkText");
		item8.setHeight("100%");
		item8.setTitleVAlign(VerticalAlignment.TOP);
		item8.setRowSpan(3);

		// 单位
		DicSelectItem item9 = new DicSelectItem();
		item9.setName("m_UnitOfMeasureCode");

		// 预计交货日期
		DateItem item10 = new DateItem();
		item10.setName("expectingDeliveryDate");
		item10.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item10.setUseTextField(true);

		ldf.setFields(item1, item2, item3, item5, item6, item7, item8, item9,
				item10);

		return ldf;
	}

	/**
	 * 表单提交操作按钮
	 * 
	 * @return
	 */
	private HLayout getSubmitLayout() {
		BtnsHLayout h = new BtnsHLayout();
		h.setWidth100();
		Button saveBtn = new Button(ui.getB().btnSaveModify());
		saveBtn.setPadding(6);
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgModifyOpSuccess());
						model.partPlanNeedOrderId = response.getData()[0]
								.getAttribute("id");
						model.defaultUploader.setVisible(true);
						model.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + model.partPlanNeedOrderId);
					}
				});
			}
		});
		Button cancelBtn = new Button(ui.getB().btnCancel());
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		h.addMember(saveBtn);
		h.addMember(cancelBtn);
		return h;
	}
}
