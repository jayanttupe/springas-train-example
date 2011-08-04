package com.skynet.spms.client.gui.partcatalog.salesCatalog.PartSaleRelease;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SalesCatalogButtonToolBar extends BaseButtonToolStript{

	private SalesCatalogListGrid salesCatalogListGrid;
	
	public SalesCatalogButtonToolBar(String moduleName,SalesCatalogListGrid salesCatalogListGrid) {
		super(moduleName);
		this.salesCatalogListGrid = salesCatalogListGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new SalesCatalogAddWindow(
					ConstantsUtil.partCatalogConstants.addSalesCatalog(), false,
					srcRect, salesCatalogListGrid,"showwindow/part_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (salesCatalogListGrid.getSelection().length == 1) {
				useWindow = new SalesCatalogModifyWindow(
						ConstantsUtil.partCatalogConstants.modifySalesCatalog(),
						false, srcRect, salesCatalogListGrid,"showwindow/part_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}

		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (salesCatalogListGrid.getSelection().length != 0) {SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value.equals(true)) {
						SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
						salesCatalogListGrid.removeSelectedData();
					}
				}
			});
			} else {
			SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
		}
		}
         else if ("publish".equalsIgnoreCase(buttonName)) {
			if (salesCatalogListGrid.getSelection().length != 0){	
				publishItem("published");
			}else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if ("cancelpublish".equalsIgnoreCase(buttonName)) {			
			if (salesCatalogListGrid.getSelection().length != 0){
				publishItem("unpublished");
			}else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}	
	}
	private void publishItem(String publishValue){
		Record record = salesCatalogListGrid.getSelectedRecord();
		DynamicForm form = new DynamicForm();
		form.setDataSource(salesCatalogListGrid.getDataSource());
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		HiddenItem hdnPubStatus = new HiddenItem("publishStatus");
		hdnPubStatus.setValue(publishValue);
		form.setFields(hdnId,hdnPubStatus);
		form.saveData();
	}
}
