package com.skynet.spms.client.gui.customerService.salesService.doQuotation;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.add.DoQuotationAddWindow;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.model.DoQuotationModelLocator;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.view.SalesInquiryViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.base.BaseBusiness;

public class DoQuotationToolStrip extends BaseButtonToolStript {
	// 报价，留言，日志，打印，导出，高级查询
	SalesInquiryModel model = SalesInquiryModel.getInstance();
	private DoQuotationModelLocator modleLocator = DoQuotationModelLocator
			.getInstance();

	public DoQuotationToolStrip(final DoQuotationListGrid quotationListgrid) {
		super(DSKey.C_DOQUOTATION);

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		ListGridRecord selectLGR = modleLocator.listGrid.getSelectedRecord();
		if ("DOQUOTATION".equals(buttonName)) {

			if (selectLGR != null) {
//				if(selectLGR.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus").equals("published")){
					DoQuotationAddWindow win = new DoQuotationAddWindow("", true,
							button.getPageRect(), null, "");
					ShowWindowTools.showWindow(button.getPageRect(),
							win, 200);
//				}else{
//					SC.say("抱歉，未发布不可报价");
//				}
				
			} else {
				SC.say("请选择一条操作项");
			}

		} else if ("VIEW".equals(buttonName)) {
			// 先给主键赋值
			model.primaryKey = modleLocator.listGrid.getSelectedRecord()
					.getAttribute("id");
			SalesInquiryViewWindow win = new SalesInquiryViewWindow("",
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		} else if ("REFRESH".equals(buttonName)) {
			BaseBusiness business = new BaseBusiness();
			business.refreshSheet(modleLocator.listGrid);
		}
	}
}
