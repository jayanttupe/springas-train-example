package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

/*import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RepairCodeDetailWindow extends Window {

	
	public RepairCodeDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect,
			ListGrid listGrid,
			String iconUrl,
			Boolean updateFlg) {
		final Window objWindow = this;
		setWidth(500);
		setHeight(300);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(280);
		mainForm.setColWidths(80, 200);
		mainForm.setHeight("90%");
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 任务编号
		final TextItem taskNumber = new TextItem("taskNumber", "任务编号");
		taskNumber.setWidth(150);
		taskNumber.setDefaultValue("业务编号系统自动生成");
		taskNumber.setDisabled(true);
		// 补码类型
		final SelectItem repairCodeType = new SelectItem("repairCodeType", "补码类型");
		repairCodeType.setWidth(150);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.repairCodeType", repairCodeType);
		repairCodeType.setHint("<font color = 'red'>*</font>");
		// 补码种类
		final SelectItem repairType = new SelectItem("repairType", "补码种类");
		repairType.setWidth(150);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.repairType", repairType);
		repairType.setHint("<font color = 'red'>*</font>");
		// 补码原因
		final TextItem repairCodeReason = new TextItem("repairCodeReason", "补码原因");
		repairCodeReason.setWidth(200);
		// 备注
		final TextAreaItem remark = new TextAreaItem("remark", "备注");
		remark.setWidth(200);
		remark.setHeight(60);

		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (repairCodeType.getAttribute("Value").equals("") ||
						repairCodeType.getAttribute("Value") == null){
					SC.say("补码类型不能为空!");
					return;
				}
				if(repairType.getAttribute("Value").equals("") ||
						repairType.getAttribute("Value") == null){
					SC.say("补码种类不能为空!");
					return;
				}
				mainForm.saveData();
				mainForm.clearValues();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("返回");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(taskNumber
						,repairCodeType
						,repairType
						,repairCodeReason
						,remark
						);

		final HLayout buttonLayout = new HLayout();
		buttonLayout.setMargin(5);
		buttonLayout.setMembersMargin(5);
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}*/