package com.skynet.spms.client.gui.customerplatform.DoQuotationPanel;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationListGrid;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.add.SalesInquiryAddWindow;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.business.SalesInquiryBusiness;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.modity.SalesInquiryModityWindow;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.view.SalesInquiryViewWindow;
import com.skynet.spms.client.gui.customerplatform.common.ModuleKey;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DoQuotationToolStrip extends BaseButtonToolStript {
	public DoQuotationToolStrip(final DoQuotationListGrid quotationListgrid) {
		super(ModuleKey.C_CONTACT);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		SalesInquiryBusiness bus = new SalesInquiryBusiness();
		SalesInquiryModel model = SalesInquiryModel.getInstance();
		if ("ADD".equals(buttonName)) {
			//新建询价单
			SalesInquiryAddWindow win = new SalesInquiryAddWindow(null,
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);

		} else if (("MODIFY").equals(buttonName)) {
			//修改询价单
			if(bus.canModifiedSheet(model.listGrid)){
				SalesInquiryModityWindow win = new SalesInquiryModityWindow(
						null, true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), win, 200);
			}
			
		} else if ("DELETE".equals(buttonName)) {
			bus.deleteSheet(model.listGrid);
		} else if ("PUBLISH".equals(buttonName)) {
			bus.publishSheet(model.listGrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			bus.cancelPublishSheet(model.listGrid);
		} else if ("VIEW".equals(buttonName)) {
			// 先给主键赋值
			model.primaryKey = model.listGrid.getSelectedRecord().getAttribute(
					"id");
			//询价单详细
			SalesInquiryViewWindow win = new SalesInquiryViewWindow(null,
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		}else if("REFRESH".equals(buttonName)){
			bus.refreshSheet(model.listGrid);
		}

	}
}
