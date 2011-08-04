package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;


public class RepairCodeButtonPanel extends BaseButtonToolStript {
	private RepairCodeListgrid repairCodeListgrid;

	public RepairCodeButtonPanel(
			final RepairCodeListgrid repairCodeListgrid) {
		super("stockServiceBusiness.basicData.repairCode");
		this.repairCodeListgrid = repairCodeListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			repairCodeListgrid.setPassByNew(true);
			useWindow = new RepairCodeEditWindow(
					ConstantsUtil.stockConstants.addRepairCode(), 
					false, rect, repairCodeListgrid, "showwindow/stock_add_01.png");
			ShowWindowTools.showWondow(rect, useWindow, -1);
		}else if ("delete".equalsIgnoreCase(buttonName)) {
			if (repairCodeListgrid.getSelection().length != 0) {
				boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
				if (isDel) {
					repairCodeListgrid.removeSelectedData();
				}
			} else {
				Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
			}
		}else if("modify".equalsIgnoreCase(buttonName)){
			if (repairCodeListgrid.getSelection().length == 1) {
				repairCodeListgrid.setPassByNew(false);
				useWindow = new RepairCodeEditWindow(
						ConstantsUtil.stockConstants.modifyRepairCode(), 
						false, rect, repairCodeListgrid, "showwindow/stock_modify_01.png");
				ShowWindowTools.showWondow(rect, useWindow, -1);
			} else {
				Window.alert(ConstantsUtil.commonConstants.alertSelectForModify());
			}
		}else if("repair_Code_Task".equalsIgnoreCase(buttonName)){
			if(repairCodeListgrid.getSelection().length != 0){
				ListGridRecord[] records =  repairCodeListgrid.getSelection();
				String[] itemIds = new String[records.length];
				for(int i=0; i<records.length; i++){
					if(records[i].getAttribute("sendStatus").equals("Send")){
						SC.say("该操作不能包含已下达任务的项，请重新选择！");
						return;
					}
					itemIds[i] = records[i].getAttribute("id");
					
				}
				DynamicForm form = new DynamicForm();
				form.setDataSource(repairCodeListgrid.getDataSource());
				form.setValue("id", "repairCodeTask");
				form.setValue("itemIds", itemIds);
				form.saveData(new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("下达补码任务完成！");
						repairCodeListgrid.fetchData();
					}
				});
				
				//继续
			}else{
				Window.alert(ConstantsUtil.commonConstants.alertSelectForModify());
			}
		}
		
	}
}