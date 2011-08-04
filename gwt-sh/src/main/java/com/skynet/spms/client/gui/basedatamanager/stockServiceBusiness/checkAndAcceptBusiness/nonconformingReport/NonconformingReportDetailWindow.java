package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class NonconformingReportDetailWindow extends Window {

	public NonconformingReportDetailWindow(String windowTitle
									,final Rectangle rect
									,String iconUrl
									,NonconformingReportListgrid listgrid
									,final Boolean updateFlg){
		final Window objWindow = this;
		setWidth(700);
		setHeight(420);

		final DynamicForm mainForm = new DynamicForm();
        mainForm.setWidth(500);
		mainForm.setHeight("90%");
		mainForm.setNumCols(4);
		mainForm.setColWidths(100, 150, 100, 150);
        mainForm.setDataSource(listgrid.getDataSource());
		if (updateFlg == true)
		{
	        Record record = listgrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
        
		// 不合格品报告编号
		final TextItem nonconformingReportNumberItem = new TextItem("nonconformingReportNumber");
		nonconformingReportNumberItem.setDefaultValue("业务编号自动生成");
		nonconformingReportNumberItem.setDisabled(true);
		// 检验单号
        final TextItem checkAndAcceptSheetNumberItem = new TextItem("checkAndAcceptSheetNumber");
        checkAndAcceptSheetNumberItem.setDisabled(true);
        // 供货单位
        final TextItem supplyUnitItem = new TextItem("supplyUnit");
        supplyUnitItem.setDisabled(true);
        // 合同编号
        final TextItem contratNumberItem = new TextItem("contratNumber");
        contratNumberItem.setDisabled(true);
		// 不合格原因
        final SelectItem nonconformingReasonTextItem = new SelectItem("nonconformingReason"); 
		nonconformingReasonTextItem.setValueMap("证件不符","其它");
		nonconformingReasonTextItem.setColSpan(3);
		nonconformingReasonTextItem.setHint("<font color='red'>*<font>");
		// 不合格描述
		final TextAreaItem nonconformingAnalysisItem = new TextAreaItem("nonconformingAnalysis"); 
		nonconformingAnalysisItem.setHint("<font color='red'>*<font>");
		nonconformingAnalysisItem.setWidth(400); 
		nonconformingAnalysisItem.setHeight(50);
		nonconformingAnalysisItem.setColSpan(3);
		// 纠正措施和完成时限
		final TextAreaItem correctiveActionItem = new TextAreaItem("correctiveAction"); 
		correctiveActionItem.setWidth(400); 
		correctiveActionItem.setHeight(50);
		correctiveActionItem.setColSpan(3);
		// MRB
		final TextAreaItem mrbItem= new TextAreaItem("mrb"); 
		mrbItem.setWidth(400); 
		mrbItem.setHeight(50); 
		mrbItem.setColSpan(3);
        // 采购实施部门处理描述
		final TextAreaItem acceptanceConclusionTextItem = new TextAreaItem("acceptanceConclusionText"); 
		acceptanceConclusionTextItem.setWidth(400); 
		acceptanceConclusionTextItem.setHeight(50);
		acceptanceConclusionTextItem.setColSpan(3);
		// 处理人
		final TextItem transactorItem = new TextItem("transactor");
		// 处理日期
		final CustomDateItem treatmentDateItem = new CustomDateItem("treatmentDate");
		
		mainForm.setFields(nonconformingReportNumberItem
				,checkAndAcceptSheetNumberItem
				,supplyUnitItem
				,contratNumberItem
				,nonconformingReasonTextItem
				,nonconformingAnalysisItem
				,correctiveActionItem
				,mrbItem
				,acceptanceConclusionTextItem
				,transactorItem
				,treatmentDateItem
				);

		// 保存按钮
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData();
				mainForm.clearValues();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		// 返回按钮
		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(returnButton);
		 
		VLayout layout = new VLayout();
		layout.setPadding(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		 
		SetWindow.SetWindowLayout(windowTitle
					,false
					,iconUrl
					,rect
					,objWindow
					,layout);
	}
}
