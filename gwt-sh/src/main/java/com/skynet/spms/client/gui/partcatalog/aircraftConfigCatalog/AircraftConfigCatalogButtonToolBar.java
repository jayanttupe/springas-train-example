/*package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoAddWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class AircraftConfigCatalogButtonToolBar extends BaseButtonToolStript{
	
	private AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid;
	
	public AircraftConfigCatalogButtonToolBar(String moduleName,AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid) {
		super(moduleName);
		this.aircraftConfigCatalogListGrid = aircraftConfigCatalogListGrid;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new IndexInfoAddWindow(
					ConstantsUtil.partCatalogConstants.addAircraftConfigCatalog(), false,
					srcRect, aircraftConfigCatalogListGrid, "icons/add.gif");
			        ShowWindowTools.showWondow(srcRect, useWindow, -1);
	     }
		else if ("modify".equalsIgnoreCase(buttonName)) {
			if (aircraftConfigCatalogListGrid.getSelection().length == 1) {
				useWindow = new AircraftConfigCatalogModifyWindow(
						ConstantsUtil.partCatalogConstants.modifyAircraftConfigCatalog(),
						false, srcRect, aircraftConfigCatalogListGrid,"icons/add.gif");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				Window.alert(ConstantsUtil.commonConstants.alertSelectForModify());
			}
		}
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (aircraftConfigCatalogListGrid.getSelection().length != 0) {
				boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
				if (isDel)
					aircraftConfigCatalogListGrid.removeSelectedData();
			} else {
				Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
			}
		}
		
	}
	
}
*/


package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class AircraftConfigCatalogButtonToolBar extends BaseButtonToolStript{

	private AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid;

	public AircraftConfigCatalogButtonToolBar(String moduleName,AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid) {
		super(moduleName);
		this.aircraftConfigCatalogListGrid = aircraftConfigCatalogListGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new AircraftConfigCatalogAddWindow(
					ConstantsUtil.partCatalogConstants.addAircraftConfigCatalog(), false,
					srcRect, aircraftConfigCatalogListGrid,"showwindow/part_add_01.png");

			ShowWindowTools.showWondow(srcRect, useWindow, -1);
			
		  }else if ("modify".equalsIgnoreCase(buttonName)) {
			if (aircraftConfigCatalogListGrid.getSelection().length == 1) {
				useWindow = new AircraftConfigCatalogModifyWindow(
						ConstantsUtil.partCatalogConstants.modifyAircraftConfigCatalog(),
						false, srcRect, aircraftConfigCatalogListGrid,"showwindow/part_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}
		}

		else if ("view".equalsIgnoreCase(buttonName)) {
			if (aircraftConfigCatalogListGrid.getSelection().length == 1) {
				//useWindow = new AircraftConfigCatalogViewWindow(aircraftConfigCatalogListGrid);
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}

		} 
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (aircraftConfigCatalogListGrid.getSelection().length != 0) {
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							aircraftConfigCatalogListGrid.removeSelectedData();
						}
					}
				});
				} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			
		}

	}
}
}


