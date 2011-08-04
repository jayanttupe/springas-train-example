package com.skynet.spms.client.gui.partcatalog.technicalDocuments;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class TechnicalDocumentButtonToolBar extends BaseButtonToolStript{
	
	private TechnicalDocumentListGrid technicalDocumentListGrid;
	
	public TechnicalDocumentButtonToolBar(String moduleName,TechnicalDocumentListGrid technicalDocumentListGrid) {
		super(moduleName);
		this.technicalDocumentListGrid = technicalDocumentListGrid;
	}
	
	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new TechnicalDocumentAddWindow(
					ConstantsUtil.partCatalogConstants.addTechnicalDocument(), false,
					srcRect, technicalDocumentListGrid, "showwindow/part_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (technicalDocumentListGrid.getSelection().length == 1) {
				useWindow = new TechnicalDocumentModifyWindow(
						ConstantsUtil.partCatalogConstants.modifyTechnicalDocument(),
						false, srcRect, technicalDocumentListGrid,"showwindow/part_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}
		}
	
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (technicalDocumentListGrid.getSelection().length != 0) {
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							technicalDocumentListGrid.removeSelectedData();
						}
					}
				});
				} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if("EXPORT".equalsIgnoreCase(buttonName)){
			/*useWindow = new ExportWindow("打印",false,srcRect,"showwindow/part_modify_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);*/
			/*Img starImg1 = new Img("/images/silk/001.jpg");
			starImg1.setImageType(ImageStyle.NORMAL);
			starImg1.setBorder("1px solid gray");
			starImg1.setWidth(starImg1.getWidth());
			starImg1.setImageWidth(starImg1.getWidth());
			Canvas.showPrintPreview(starImg1);*/
		}
		
	}

}
