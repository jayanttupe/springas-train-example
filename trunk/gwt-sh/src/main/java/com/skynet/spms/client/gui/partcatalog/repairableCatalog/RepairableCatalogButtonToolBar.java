package com.skynet.spms.client.gui.partcatalog.repairableCatalog;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoAddWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoModifyWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class RepairableCatalogButtonToolBar extends BaseButtonToolStript{
	
	private IndexInfoListGrid indexInfoListGrid;
	
	public RepairableCatalogButtonToolBar(String moduleName,IndexInfoListGrid indexInfoListGrid) {
		super(moduleName);
		this.indexInfoListGrid = indexInfoListGrid;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new IndexInfoAddWindow(
					ConstantsUtil.partCatalogConstants.addIndexInfo(), false,
					srcRect, indexInfoListGrid, "showwindow/part_add_01.png");
			        ShowWindowTools.showWondow(srcRect, useWindow, -1);
		  }else if ("modify".equalsIgnoreCase(buttonName)) {
			if (indexInfoListGrid.getSelection().length == 1) {
				useWindow = new IndexInfoModifyWindow(
						ConstantsUtil.partCatalogConstants.modifyIndexInfo(),
						false, srcRect, indexInfoListGrid,"showwindow/part_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}
		}

		else if ("view".equalsIgnoreCase(buttonName)) {
			if (indexInfoListGrid.getSelection().length == 1) {
				//useWindow = new IndexInfoListGrid(indexInfoListGrid);
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}

		} 
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (indexInfoListGrid.getSelection().length != 0) {
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							indexInfoListGrid.removeSelectedData();
						}
					}
				});
				} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}
		}


		else if ("publish".equalsIgnoreCase(buttonName)) {
			
			if (indexInfoListGrid.getSelection().length != 0){	
				publishItem("published");
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if ("cancelpublish".equalsIgnoreCase(buttonName)) {
					
			if (indexInfoListGrid.getSelection().length != 0){
				publishItem("unpublished");
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		
	}
		
	private void publishItem(String publishValue){
		Record record = indexInfoListGrid.getSelectedRecord();
		DynamicForm form = new DynamicForm();
		form.setDataSource(indexInfoListGrid.getDataSource());
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		HiddenItem hdnPubStatus = new HiddenItem("publishStatus");
		hdnPubStatus.setValue(publishValue);
		form.setFields(hdnId,hdnPubStatus);
		form.saveData();
		
	}

}